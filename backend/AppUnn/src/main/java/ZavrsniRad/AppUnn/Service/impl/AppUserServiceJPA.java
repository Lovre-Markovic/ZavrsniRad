package ZavrsniRad.AppUnn.Service.impl;

import ZavrsniRad.AppUnn.Entity.ErrorMsg;
import ZavrsniRad.AppUnn.Entity.Level;
import ZavrsniRad.AppUnn.Entity.Users;
import ZavrsniRad.AppUnn.Repository.AppUserRepository;
import ZavrsniRad.AppUnn.Repository.LevelRepository;
import ZavrsniRad.AppUnn.Service.AppUserService;
import ZavrsniRad.AppUnn.Service.RequestDeniedException;
import ZavrsniRad.AppUnn.Service.dto.EditProfileDTO;
import ZavrsniRad.AppUnn.Service.dto.LoginInfoDTO;
import ZavrsniRad.AppUnn.Service.dto.PlayerDTO;
import ZavrsniRad.AppUnn.Service.dto.ProfileViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceJPA implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;

    private final LevelRepository levelRepository;

    @Autowired
    public AppUserServiceJPA(PasswordEncoder passwordEncoder,
                             AppUserRepository userRepository, LevelRepository levelRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;


        this.levelRepository = levelRepository;
    }

    @Override
    public List<Users> listAll() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createAppUser(Users user) {
        if(user.getPassword().length() == 0) {
            throw new RequestDeniedException("Password can't be blank");
        }
        if(userRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<ErrorMsg>(
                    new ErrorMsg("E-mail je već u uporabi"),
                    HttpStatus.BAD_REQUEST);


        }


        if(userRepository.findByUsername(user.getUsername())!=null) {
            return new ResponseEntity<ErrorMsg>(
                    new ErrorMsg("Korisničko ime je zauzeto"),HttpStatus.BAD_REQUEST
            );
        }
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPoints(0);
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setAdmin(user.isAdmin());

        Optional<Level> levelOptional = levelRepository.findById(1l);
        if (!levelOptional.isPresent()) {
            return new ResponseEntity<>("Level not found", HttpStatus.BAD_REQUEST);
        }
        Level level = levelOptional.get();

        user.setLevel(level);
        System.out.println(user.isAdmin());






        Users usr = userRepository.save(user);





        return new ResponseEntity<Users>(
                usr,
                HttpStatus.OK);


    }

    @Override
    public boolean checkLogin(LoginInfoDTO info) {
        boolean loggedIn = false;
        String username = info.getUsername();
        System.out.println(username);

        Users user = userRepository.findByUsername(username);
        if(user != null) {

            //BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            boolean isPasswordMatches = passwordEncoder
                    .matches(info.getPassword(), user.getPassword());
            if (isPasswordMatches) {

                loggedIn = true;
            }

        }
        return loggedIn;

    }

    public ResponseEntity<?> editProfile(EditProfileDTO data) {

        String nickname = data.getUsername();

        String newPassword = data.getPassword();

        String newEmail = data.getEmail();
        //System.out.println(newEmail);
        String newFirstName = data.getFirstName();
        //System.out.println(newFirstName);
        String newLastName = data.getLastName();

        Users user = userRepository.findByUsername(nickname);
        String oldPassword = user.getPassword();
        String oldEmail = user.getEmail();
        String oldFirstName = user.getFirstName();
        String oldLastName = user.getLastName();

        if (newPassword == null) {
            newPassword = "";
        }
        if (newEmail == null) {
            System.out.println("null je");
            newEmail = "";
        }
        if (newFirstName == null) {
            System.out.println("null je");
            newFirstName = "";
        }
        if (newLastName == null) {
            newLastName = "";
        }

        if (newEmail.isBlank()) {
            newEmail = oldEmail;
        }
        if (newPassword.isBlank()) {
            newPassword = oldPassword;
        }
        if (newFirstName.isBlank()) {
            newFirstName = oldFirstName;
        }
        if (newLastName.isBlank()) {
            newLastName = oldLastName;
        }

        //System.out.println(newEmail);

        if (!newEmail.equals(oldEmail)) {
            if (userRepository.findByEmail(newEmail) == null) {
                userRepository.updateProfile(passwordEncoder.encode(newPassword), newEmail, nickname, newFirstName, newLastName);
                return new ResponseEntity<String>(
                        "",
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<ErrorMsg>(
                        new ErrorMsg("E-mail u uporabi"),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            userRepository.updateProfile(passwordEncoder.encode(newPassword), newEmail, nickname, newFirstName, newLastName);
            return new ResponseEntity<String>(
                    "",
                    HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<ProfileViewDTO> viewProfile(PlayerDTO dto) {
        Users user = userRepository.findByUsername(dto.getUsername());


        ProfileViewDTO dto2 = new ProfileViewDTO(user);
        return new ResponseEntity<>(
                dto2,HttpStatus.OK
        );

    }
    @Override
    public ResponseEntity<?> deleteUser(String username) {

            userRepository.deleteByUsername(username);


        if(userRepository.findByUsername(username) == null) {

            return new ResponseEntity<String>(
                    "",
                    HttpStatus.OK
            );


        } else {
            return new ResponseEntity<ErrorMsg>(
                    new ErrorMsg("Pogreška kod brisanja"),
                    HttpStatus.BAD_REQUEST);

        }


    }

}
