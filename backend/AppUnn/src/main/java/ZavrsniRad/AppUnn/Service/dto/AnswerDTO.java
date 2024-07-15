package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Answer;

public class AnswerDTO {

    private String text;

    private boolean is_correct;

    public AnswerDTO(Answer answer) {
        this.text = answer.getText();
        this.is_correct = answer.isCorrect();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}
