package ZavrsniRad.AppUnn.Repository;

import ZavrsniRad.AppUnn.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface AppUserRepository extends JpaRepository<Users, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.password= :newPassword, u.email= :newEmail, u.firstName= :newFirstName, u.lastName= :newLastName WHERE u.username= :username"
    )
    void updateProfile(@Param("newPassword") String newPassword, @Param("newEmail") String newEmail,
                       @Param("username") String username, @Param("newFirstName") String newFirstName,  @Param("newLastName") String newLastName);

    Users findByUsername(String username);

    Users findByEmail(String email);

    List<Users> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Users u WHERE u.username =:username")
    void deleteByUsername(@Param("username") String username);



}
