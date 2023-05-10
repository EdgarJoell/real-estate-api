package com.example.realestate.security;


import com.example.realestate.model.Agent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyAgentDetails implements UserDetails {
    private final Agent agent;

    public MyAgentDetails(Agent agent) {
        this.agent = agent;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return agent.getPassword();
    }

    @Override
    public String getUsername() {
        return agent.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Agent getAgent() {
        return agent;
    }
}
