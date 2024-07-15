package ZavrsniRad.AppUnn.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class Users {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name ="points")
    private int points;

    @ManyToOne
    @JoinColumn(name="levelid",referencedColumnName = "id",nullable=false)
    private Level level;

    public Users(String username, boolean isAdmin, String firstName, String lastName, String email, String password, int points) {
        this.username = username;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.points = points;

    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}