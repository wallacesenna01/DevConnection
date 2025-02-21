package com.br.wallaceartur.DevConnection;

import com.br.wallaceartur.DevConnection.model.User;
import com.br.wallaceartur.DevConnection.model.UserProfile;
import com.br.wallaceartur.DevConnection.repositories.UserProfileRepository;
import com.br.wallaceartur.DevConnection.repositories.UserRepository;
import com.br.wallaceartur.DevConnection.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("bios") String bios,
            @RequestParam("favTech") String favTech,
            @RequestParam("profilePictureUrl") MultipartFile profilePicture,
            @RequestParam("coverPictureUrl") MultipartFile coverPicture) {


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UserProfile userProfile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        // 3. Atualizar os campos do perfil
        userProfile.setName(name);
        userProfile.setBios(bios);
        userProfile.setFavTech(favTech);


        userProfileRepository.save(userProfile);

        return ResponseEntity.ok("Perfil atualizado com sucesso!");
    }
}
