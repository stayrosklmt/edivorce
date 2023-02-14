package com.divorce.edivorce.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.divorce.edivorce.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByUsername(String username);

  User findUserById(UUID id);

  List<User> findAll();

  void deleteUserById(UUID id);

}
