package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Users;

public class PlayerDTO {

    private String username;
    private Long levelId=1L;

    private int points=0;


    private boolean admin;



    public PlayerDTO() {

    }
    public PlayerDTO(Users user) {
        this.username = user.getUsername();
        this.levelId = user.getLevel().getId();
        this.points= user.getPoints();
        this.admin = user.isAdmin();


    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
