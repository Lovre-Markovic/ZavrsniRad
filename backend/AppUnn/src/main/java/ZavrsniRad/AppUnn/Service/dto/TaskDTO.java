package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private String taskText;

    private List<AnswerDTO> answers = new ArrayList<>();

    private String explanation;

    public TaskDTO(Task task) {

        this.taskText = task.getText();
        this.explanation = task.getExplanation();



    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
