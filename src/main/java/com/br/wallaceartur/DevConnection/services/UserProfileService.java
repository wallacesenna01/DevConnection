package com.br.wallaceartur.DevConnection.services;

import com.br.wallaceartur.DevConnection.dtos.UpdateProfileDto;
import com.br.wallaceartur.DevConnection.model.User;
import com.br.wallaceartur.DevConnection.model.UserProfile;
import com.br.wallaceartur.DevConnection.repositories.UserProfileRepository;
import com.br.wallaceartur.DevConnection.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserProfileService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public String saveImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Arquivo de imagem inválido");
        }

        // criar um diretorio de upload se nao existir
        File upLoadDir = new File(UPLOAD_DIR);
        if(!upLoadDir.exists()) {
            upLoadDir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.copy(image.getInputStream(), filePath);
        return fileName;
    }

    public UserProfile updateProfile(UpdateProfileDto updateProfileDto, MultipartFile profilePicture, MultipartFile coverPicture) throws IOException {

        UserProfile userProfile = getAuthenticatedUserProfile();
        if (updateProfileDto.getName() != null) userProfile.setName(updateProfileDto.getName());
        if (updateProfileDto.getBios() != null) userProfile.setBios(updateProfileDto.getBios());
        if (updateProfileDto.getFavTech() != null) userProfile.setFavTech(updateProfileDto.getFavTech());

        if (profilePicture != null) {
            String profilePicName = saveImage(profilePicture);
            userProfile.setProfilePictureUrl(UPLOAD_DIR + profilePicName);
        }

        if (coverPicture != null) {
            String coverPicName = saveImage(coverPicture);
            userProfile.setCoverPictureUrl(UPLOAD_DIR + coverPicName);
        }

        return  userProfileRepository.save(userProfile);
    }


    public UserProfile getAuthenticatedUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário nao encontrado");
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));



        return  userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Perfil nao encontrado"));
    }


 }
