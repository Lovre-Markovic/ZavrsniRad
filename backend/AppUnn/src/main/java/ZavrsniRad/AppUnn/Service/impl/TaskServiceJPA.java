package ZavrsniRad.AppUnn.Service.impl;

import ZavrsniRad.AppUnn.Entity.Answer;
import ZavrsniRad.AppUnn.Entity.ErrorMsg;
import ZavrsniRad.AppUnn.Entity.Level;
import ZavrsniRad.AppUnn.Entity.Task;
import ZavrsniRad.AppUnn.Repository.AnswerRepository;
import ZavrsniRad.AppUnn.Repository.LevelRepository;
import ZavrsniRad.AppUnn.Repository.TaskRepository;
import ZavrsniRad.AppUnn.Service.TaskService;
import ZavrsniRad.AppUnn.Service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
public class TaskServiceJPA implements TaskService {

    private final TaskRepository taskRepository;
    private final AnswerRepository answerRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public TaskServiceJPA(TaskRepository taskRepository, AnswerRepository answerRepository,
                          LevelRepository levelRepository) {

        this.taskRepository = taskRepository;
        this.answerRepository = answerRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public List<TaskDTO> getTasksForSession(Long level) {
        List<Task> listOfAllTasks = taskRepository.findByLevelId(level);

        List<TaskDTO> sessionList = new ArrayList<>();
        Random random=new Random();
        for(int i =0;i<5;i++) {
            int randomIndex = random.nextInt(listOfAllTasks.size());
            Task task = listOfAllTasks.get(randomIndex);
            List<Answer> answers = answerRepository.findByTaskId(task.getId());
            List<AnswerDTO> answersDTO = new ArrayList<>();
            for(Answer answer : answers) {
                AnswerDTO dto = new AnswerDTO(answer);
                answersDTO.add(dto);
            }

            TaskDTO dto = new TaskDTO(task);
            dto.setAnswers(answersDTO);
            sessionList.add(dto);
            listOfAllTasks.remove(randomIndex);
        }
        return sessionList;

    }
    @Override
    public ResponseEntity<?> addTask(AdminTaskDTO task) {
        if(taskRepository.findByTaskId(task.getId()) != null) {
            return new ResponseEntity<>(new ErrorMsg("Id zadatka već u upotrebi, pogledajte Id-eve zadataka"),
                    HttpStatus.NOT_ACCEPTABLE);
        }





        Optional<Level> currentLevelOpt = levelRepository.findById(task.getLevelId());
        Level currentLevel;
        if (currentLevelOpt.isPresent()) {
            currentLevel = currentLevelOpt.get();
        } else {
            return new ResponseEntity<>(new ErrorMsg("Greška kod levela"),
                    HttpStatus.NOT_ACCEPTABLE);

        }

        try {

            Task newTask = new Task(task.getId(),currentLevel, task.getText(), task.getExplanation());
            Task myTask = taskRepository.save(newTask);
            return new ResponseEntity<>(myTask, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMsg("Zadatak se nije uspio kreirati"),
                    HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Override
    public ResponseEntity<?> deleteTask(TaskIdDTO task) {
        taskRepository.deleteById(task.getId());
        if(taskRepository.findByTaskId(task.getId()) == null) {
            return new ResponseEntity<>(
                    "", HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(new ErrorMsg("Greška kod brisanja"), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> deleteAnswer(AnswerIdDTO answer) {
        answerRepository.deleteById(answer.getId());
        System.out.println(answer.getId());
        if(answerRepository.findByAnswerId(answer.getId()) == null) {
            return new ResponseEntity<>(
                    "", HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(new ErrorMsg("Greška kod brisanja"), HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public boolean AddAnswer(AdminAnswerDTO answer) {
        System.out.println(answer.getIsCorrect());
        if(answerRepository.findByAnswerId(answer.getId())==null) {
            Task t = taskRepository.findByTaskId(answer.getTaskId());
            List<Answer> answers = answerRepository.findByTaskId(answer.getTaskId());
            if(answers.size() >=4) {
                return false;
            }
            Answer ans = new Answer(answer.getId(), answer.getText(), t, answer.getIsCorrect());
            answerRepository.save(ans);
            return true;

        } else {
            return false;
        }


    }
}
