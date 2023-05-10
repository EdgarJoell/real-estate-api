package com.example.realestate.repository;

import com.example.realestate.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    //Login
    boolean existsByEmailAddress(String email);

    //Register
    Agent findAgentByEmailAddress(String email);
}
