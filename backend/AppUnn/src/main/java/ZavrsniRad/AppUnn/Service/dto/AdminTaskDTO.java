package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Answer;
import ZavrsniRad.AppUnn.Entity.Level;
import jakarta.persistence.*;

public class AdminTaskDTO {


    private Long id;

    private String text;



    private Long levelId;




    private String explanation;

    public AdminTaskDTO(Long id, String text, Long levelId, String explanation) {
        this.id = id;
        this.text = text;
        this.levelId = levelId;

        this.explanation = explanation;
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

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }



    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
