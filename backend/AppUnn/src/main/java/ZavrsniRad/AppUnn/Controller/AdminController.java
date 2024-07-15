package ZavrsniRad.AppUnn.Controller;

import ZavrsniRad.AppUnn.Entity.*;
import ZavrsniRad.AppUnn.Repository.AnswerRepository;
import ZavrsniRad.AppUnn.Repository.AppUserRepository;
import ZavrsniRad.AppUnn.Repository.LevelRepository;
import ZavrsniRad.AppUnn.Repository.TaskRepository;
import ZavrsniRad.AppUnn.Service.AppUserService;
import ZavrsniRad.AppUnn.Service.TaskService;
import ZavrsniRad.AppUnn.Service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/AppUnn/Admin")
public class AdminController {

    private final AppUserRepository userRepository;
    private final TaskRepository taskRepository;

    private final AppUserService userService;

    private final AnswerRepository answerRepository;
    private final TaskService taskService;
    private final LevelRepository levelRepository;

    @Autowired
    public AdminController(AppUserRepository userRepository, TaskRepository taskRepository, AppUserService userService,  AnswerRepository answerRepository, TaskService taskService,
                           LevelRepository levelRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;

        this.userService = userService;

        this.answerRepository = answerRepository;
        this.taskService = taskService;
        this.levelRepository = levelRepository;
    }

    @PostMapping("/AddUser")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        return userService.createAppUser(user);

    }
    @PostMapping("/DeleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody Users user) {
            return userService.deleteUser(user.getUsername());

    }
    @GetMapping("/Users")
    public ResponseEntity<List<Users>> listAllUsers() {
        List<Users> lista = userService.listAll();
        return new ResponseEntity<>(
                lista, HttpStatus.OK

        );


    }

    @PostMapping("/Tasks/{levelId}")
    public ResponseEntity<?> listTasks(@RequestBody LevelIdDTO level) {
        List<Task> listOfTasks = taskRepository.findByLevelId(level.getLevelId());
        if(!listOfTasks.isEmpty()) {
            return new ResponseEntity<>(
                    listOfTasks, HttpStatus.OK
            );
        } else {
            ErrorMsg msg = new ErrorMsg("Nema zadataka za ovaj level");
            return new ResponseEntity<ErrorMsg>(
                    msg, HttpStatus.UNAUTHORIZED
            );
        }


    }
    @GetMapping("/answers")
    public ResponseEntity<List<AdminAnswerDTO>> listAllAnswers() {

        List<Answer> odgovori =  answerRepository.findAll();
        List<AdminAnswerDTO> answerDTOS = new ArrayList<>();
        for(Answer ans : odgovori) {
            AdminAnswerDTO dto = new AdminAnswerDTO(ans);
            answerDTOS.add(dto);
        }
        return new ResponseEntity<>(
                answerDTOS, HttpStatus.OK
        );

    }
    //AddTask
    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody AdminTaskDTO task) {
        return taskService.addTask(task);

    }

    @PostMapping("/addAnswer")
    public ResponseEntity<?> addAnswer(@RequestBody AdminAnswerDTO answer) {
        if(taskService.AddAnswer(answer)) {
            return new ResponseEntity<String>(
                    "", HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(new ErrorMsg("Neuspješno dodan odgovor, zadatak već sadrži 4 odgovora"), HttpStatus.BAD_REQUEST);

            }
        }


    @PostMapping("/deleteAnswer")
    public ResponseEntity<?> deleteAnswer(@RequestBody AnswerIdDTO answer) {
        return taskService.deleteAnswer(answer);
    }

    @PostMapping("/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestBody TaskIdDTO task) {
        return taskService.deleteTask(task);

    }
}


