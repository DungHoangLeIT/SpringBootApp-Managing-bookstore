package com.example.employee.repository;

import com.example.employee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findOneByEmailIgnoreCaseAndDeleted(String email, boolean deleted);

    Optional<User> findFirstByMobileAndDeleted(String mobile, boolean deleted);

    boolean existsByMobileAndDeleted(String mobile, boolean deleted);

    boolean existsByEmailAndDeleted(String email, boolean deleted);

    User save(User user);


}
