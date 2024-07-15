package ZavrsniRad.AppUnn.Controller;

import ZavrsniRad.AppUnn.Entity.ErrorMsg;
import ZavrsniRad.AppUnn.Entity.Users;
import ZavrsniRad.AppUnn.Repository.AppUserRepository;
import ZavrsniRad.AppUnn.Service.AppUserService;
import ZavrsniRad.AppUnn.Service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/AppUnn")
public class LoginController {

    private final AppUserRepository userRepository;

    private final AppUserService userService;


    @Autowired
    public LoginController(AppUserRepository userRepository, AppUserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;

    }

    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(@RequestBody LoginInfoDTO info ) {


        if(userService.checkLogin(info)) {

            Users user = userRepository.findByUsername(info.getUsername());
            PlayerDTO dtoPlayer = new PlayerDTO(user);

            return new ResponseEntity<>(dtoPlayer, HttpStatus.OK);
        } else {
            ErrorMsg msg = new ErrorMsg("Pogrešno korisničko ime ili lozinka");
            return new ResponseEntity<ErrorMsg>(
                    msg,
                    HttpStatus.UNAUTHORIZED);

        }


    }
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Users user) {

        return userService.createAppUser(user);

    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileViewDTO> viewProfile(@RequestBody PlayerDTO username) {

       return userService.viewProfile(username);
    }
    @PostMapping("/editProfile")
    public ResponseEntity<?> editProfile(@RequestBody EditProfileDTO data) {
        return userService.editProfile(data);
    }



}
