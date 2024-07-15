package ZavrsniRad.AppUnn.Service.dto;

public class EditProfileDTO {

    private String password;

    private String username;

    private String firstName;
    private String lastName;



    private String email;


    public EditProfileDTO() {
    }

    public EditProfileDTO(String username, String newPassword, String firstName, String lastName, String newEmail) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=newEmail;
        this.password=newPassword;

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String nickname) {
        this.username = nickname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
