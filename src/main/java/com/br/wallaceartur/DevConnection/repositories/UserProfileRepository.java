package com.br.wallaceartur.DevConnection.repositories;
import com.br.wallaceartur.DevConnection.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUsername(String username);
}
