package ZavrsniRad.AppUnn.Service.dto;

public class SessionLogDTO {
    private String username;
    private int correctAnswers;

    public SessionLogDTO(String username, int correctAnswers) {
        this.username = username;
        this.correctAnswers = correctAnswers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
