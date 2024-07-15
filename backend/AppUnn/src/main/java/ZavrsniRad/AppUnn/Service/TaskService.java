package ZavrsniRad.AppUnn.Service;

import ZavrsniRad.AppUnn.Entity.Answer;
import ZavrsniRad.AppUnn.Entity.Task;
import ZavrsniRad.AppUnn.Service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    List<TaskDTO> getTasksForSession(Long level);

     ResponseEntity<?> addTask(AdminTaskDTO task);
    ResponseEntity<?> deleteTask(TaskIdDTO task);

    ResponseEntity<?> deleteAnswer(AnswerIdDTO answer);

    boolean AddAnswer(AdminAnswerDTO answer);
}
