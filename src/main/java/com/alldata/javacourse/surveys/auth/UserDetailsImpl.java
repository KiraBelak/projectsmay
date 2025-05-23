package com.alldata.javacourse.surveys.auth;

import com.alldata.javacourse.surveys.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final String user;
    private final String password;
    private final List<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.user = user.getName();
        this.password = user.getPassword();
        this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user;
    }
}
