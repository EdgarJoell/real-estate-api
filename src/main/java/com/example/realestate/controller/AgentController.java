package com.example.realestate.controller;

import com.example.realestate.model.Agent;
import com.example.realestate.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/")
public class AgentController {
    private AgentService agentService;

    @Autowired
    public void setAgentService(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping(path = "/register/")
    @ResponseStatus(HttpStatus.CREATED)
    public Agent registerAgent(@RequestBody Agent agentObject) {
        return agentService.registerAgent(agentObject);
    }
}
