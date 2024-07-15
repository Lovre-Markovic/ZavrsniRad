package ZavrsniRad.AppUnn.Service.dto;

public class GetTasksDTO {
    private String username;
    private long levelId;

    public GetTasksDTO(String username, long levelId) {
        this.username = username;
        this.levelId = levelId;
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
}
