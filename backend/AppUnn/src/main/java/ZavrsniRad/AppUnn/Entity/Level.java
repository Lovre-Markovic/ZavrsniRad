package ZavrsniRad.AppUnn.Entity;

import jakarta.persistence.*;

@Entity
public class Level {

    @Id
    private Long Id;



    private int pointsRequired;

    public Level() {
    }

    public Level(int pointsRequired) {


        this.pointsRequired = pointsRequired;
    }



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }
}
