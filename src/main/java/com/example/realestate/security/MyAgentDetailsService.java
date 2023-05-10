package com.example.realestate.security;

import com.example.realestate.model.Agent;
import com.example.realestate.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAgentDetailsService implements UserDetailsService {

    private AgentService agentService;

    @Autowired
    public void setAgentService(AgentService agentService) {
        this.agentService = agentService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Agent agent = agentService.findAgentByEmailAddress(username);
        return null;
    }
}
