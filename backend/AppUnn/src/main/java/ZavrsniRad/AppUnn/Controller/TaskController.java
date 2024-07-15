package ZavrsniRad.AppUnn.Controller;

import ZavrsniRad.AppUnn.Entity.Level;
import ZavrsniRad.AppUnn.Entity.Users;
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

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/AppUnn/Tasks")
public class TaskController {
    private final AppUserService userService;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final AppUserRepository userRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public TaskController(AppUserService userService, TaskRepository taskRepository, TaskService taskService, AppUserRepository userRepository, LevelRepository levelRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;

    }

    @PostMapping("/startSession/{levelId}")
    public ResponseEntity<SessionDetailsDTO> getTasks(@RequestBody GetTasksDTO input) {
        // Replace this with your actual player data retrieval code
        System.out.println(input.getUsername());
        SessionDetailsDTO dto = new SessionDetailsDTO(input.getUsername(), input.getLevelId(),
                taskService.getTasksForSession(input.getLevelId()));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping("/endSession")
    public ResponseEntity<PlayerDTO> endSession(@RequestBody SessionLogDTO log) {
        Users player = userRepository.findByUsername(log.getUsername());
        int points = player.getPoints() + log.getCorrectAnswers()*2;
        player.setPoints(points);

        Optional<Level> currentLevelOpt = levelRepository.findById(player.getLevel().getId());
        if (currentLevelOpt.isPresent()) {
            Level currentLevel = currentLevelOpt.get();
            Optional<Level> nextLevelOpt = levelRepository.findById(currentLevel.getId() + 1);
            if (nextLevelOpt.isPresent()) {
                Level nextLevel = nextLevelOpt.get();
            if (points >= nextLevel.getPointsRequired()) {

                player.setLevel(nextLevel);
                }
            }
        }

        Users updatedPlayer = userRepository.save(player);
        PlayerDTO dto = new PlayerDTO(updatedPlayer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
