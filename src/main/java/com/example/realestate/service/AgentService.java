package com.example.realestate.service;

import com.example.realestate.model.Agent;
import com.example.realestate.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private AgentRepository agentRepository;

    @Autowired
    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent findAgentByEmailAddress(String email) {
        return agentRepository.findAgentByEmailAddress(email);
    }
}
