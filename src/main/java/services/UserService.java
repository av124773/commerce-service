package services;

import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // todo
//    public User createUser() {
//
//    }
//
//    public User updateUser() {
//
//    }
//
//    public User deleteUser() {
//
//    }
}
