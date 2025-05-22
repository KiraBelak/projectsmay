package com.alldata.jproject.service.impl;

import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerNewUser(User user){return userRepository.save(user);}

    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    @Override
    public UserDetails loadUserByUsername(String userIdentification) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userIdentification).orElseGet(()->userRepository.findByEmail(userIdentification).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado")));

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
