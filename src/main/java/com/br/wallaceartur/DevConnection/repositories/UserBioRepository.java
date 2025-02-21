package com.br.wallaceartur.DevConnection.repositories;

import com.br.wallaceartur.DevConnection.model.User;
import com.br.wallaceartur.DevConnection.model.UserBio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBioRepository extends JpaRepository<UserBio, Long> {

    Optional <UserBio> findByUser(User user);
}
