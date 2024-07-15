package ZavrsniRad.AppUnn.Service;

import ZavrsniRad.AppUnn.Entity.Users;
import ZavrsniRad.AppUnn.Service.dto.EditProfileDTO;
import ZavrsniRad.AppUnn.Service.dto.LoginInfoDTO;
import ZavrsniRad.AppUnn.Service.dto.PlayerDTO;
import ZavrsniRad.AppUnn.Service.dto.ProfileViewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppUserService {

    List<Users> listAll();

    ResponseEntity<?> createAppUser(Users user);

    boolean checkLogin(LoginInfoDTO dto);

    ResponseEntity<?> editProfile(EditProfileDTO data);

    ResponseEntity<ProfileViewDTO> viewProfile(PlayerDTO dto);

     ResponseEntity<?> deleteUser(String username);


}
