package com.kotuko.kotuko.respository;

import com.kotuko.kotuko.dto.UserProjection;
import com.kotuko.kotuko.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    Optional<UserProjection> findUserByUsername(String username);

    Optional<UserProjection> findUserByEmail(String email);

    Page<UserProjection> findByFirstName(String firstName, Pageable pageable);

    Page<UserProjection> findByLastName(String lastName,Pageable pageable);

}
