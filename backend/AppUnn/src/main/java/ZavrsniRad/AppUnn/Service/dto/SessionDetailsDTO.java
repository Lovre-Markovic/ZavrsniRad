package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Task;

import java.util.List;

public class SessionDetailsDTO {
    private String username;
    private Long levelId;
    private List<TaskDTO> tasks;

    public SessionDetailsDTO(String username, Long levelId, List<TaskDTO> tasks) {
        this.username = username;
        this.levelId = levelId;
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
