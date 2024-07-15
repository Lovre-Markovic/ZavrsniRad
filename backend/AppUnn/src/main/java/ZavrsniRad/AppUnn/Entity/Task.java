package ZavrsniRad.AppUnn.Entity;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "levelid",referencedColumnName = "id",nullable=false)
    private Level level;

    private String explanation;

    public Task() {
    }

    public Task(Long id, Level levelId, String text,  String explanation) {
        this.id = id;
        this.level = levelId;

        this.text = text;
        this.explanation = explanation;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Level getLevelId() {
        return level;
    }

    public void setLevelId(Level levelId) {
        this.level = levelId;
    }



    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
