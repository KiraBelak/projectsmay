package com.alldata.javacourse.surveys.auth;

import com.alldata.javacourse.surveys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConverter implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userService.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }
}
