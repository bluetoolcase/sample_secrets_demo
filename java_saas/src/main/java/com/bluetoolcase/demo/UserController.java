package com.bluetoolcase.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluetoolcase.demo.User;
import com.bluetoolcase.demo.datalayer.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

	@GetMapping("/user")
	public User greeting(@RequestParam(name = "email") String email) {
        final com.bluetoolcase.demo.datalayer.User userDto = userService.getByEmail(email);
		return new User(userDto.getId(), userDto.getEmail(), userDto.getName());
	}
}
