package com.br.wallaceartur.DevConnection;

import com.br.wallaceartur.DevConnection.dtos.UpdateProfileDto;
import com.br.wallaceartur.DevConnection.model.UserProfile;
import com.br.wallaceartur.DevConnection.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
   private UserProfileService userProfileService;

    @PutMapping("/update")
    public ResponseEntity<UserProfile> updateProfile(@ModelAttribute UpdateProfileDto updateProfileDto,
                                                     @RequestParam(value = "profilePicture", required = false)MultipartFile profilePicture,
                                                     @RequestParam(value = "coverPicture", required = false) MultipartFile coverPicture
                                                     ) {
        try {
            UserProfile updateProfile = userProfileService.updateProfile(updateProfileDto,profilePicture,coverPicture);
            return ResponseEntity.ok(updateProfile);
        }
        catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
