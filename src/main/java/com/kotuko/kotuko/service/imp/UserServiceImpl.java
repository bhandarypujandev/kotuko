package com.kotuko.kotuko.service.imp;

import com.kotuko.kotuko.dto.UserProjection;
import com.kotuko.kotuko.dto.UserReq;
import com.kotuko.kotuko.dto.UserResponse;
import com.kotuko.kotuko.entity.User;
import com.kotuko.kotuko.respository.UserRepository;
import com.kotuko.kotuko.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final static int pageSize = 10;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public Optional<UserProjection> getUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<UserProjection> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Page<UserProjection> getUserByFirstName(String firstName, int pageNumber) {
        return userRepository.findByFirstName(firstName, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<UserProjection> getUserByLastName(String firstName, int pageNumber) {
        return userRepository.findByLastName(firstName, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<UserResponse> editUser(int id, UserReq editRequest) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty())
            return ResponseEntity.notFound().build();
        else{
            User user = byId.get();
            user.setUsername(editRequest.getUsername());
            user.setEmail(editRequest.getEmail());
            user.setFirstName(editRequest.getFirstName());
            user.setLastName(editRequest.getLastName());
            user.setDateOfBirth(editRequest.getDateOfBirth());
            return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(userRepository.save(user)));
        }

    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User userToDelete = byId.get();
            userRepository.delete(userToDelete);

            return ResponseEntity.ok("User with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
