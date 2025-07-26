package com.bluetoolcase.demo.datalayer;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final String name,final String email) {
        final User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
    }

    public User getByEmail(final String email) {
        return userRepository.findByEmail(email);
    }
}

