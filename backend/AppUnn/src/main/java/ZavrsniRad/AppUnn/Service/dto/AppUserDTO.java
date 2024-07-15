package ZavrsniRad.AppUnn.Service.dto;

import ZavrsniRad.AppUnn.Entity.Users;

public class AppUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private String email;


    public AppUserDTO(Users user)  {

        this.username = user.getUsername();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
        this.isAdmin=user.isAdmin();


    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
