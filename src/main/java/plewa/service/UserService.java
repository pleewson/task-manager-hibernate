package plewa.service;

import org.springframework.stereotype.Service;
import plewa.entity.User;
import plewa.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String username, String email) {
        User user = new User(username, email);
        userRepository.save(user);
    }
}
