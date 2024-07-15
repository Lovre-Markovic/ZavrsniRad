package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Answer;

public class AdminAnswerDTO {

    private Long id;

    private String text;

    private Long taskId;

    private boolean isCorrect;

    public AdminAnswerDTO() {
    }

    public AdminAnswerDTO(Long id, String text, Long taskId, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.taskId = taskId;
        this.isCorrect = isCorrect;
    }
    public AdminAnswerDTO(Answer ans) {
        this.id = ans.getId();
        this.text = ans.getText();
        this.taskId = ans.getTask().getId();
        this.isCorrect = ans.isCorrect();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
