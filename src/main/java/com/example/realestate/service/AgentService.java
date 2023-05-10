package com.example.realestate.service;

import com.example.realestate.exception.InformationExistException;
import com.example.realestate.model.Agent;
import com.example.realestate.model.request.LoginRequest;
import com.example.realestate.model.response.LoginResponse;
import com.example.realestate.repository.AgentRepository;
import com.example.realestate.security.JWTUtils;
import com.example.realestate.security.MyAgentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AgentService {

    private AgentRepository agentRepository;
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private JWTUtils jwtUtils;

    private MyAgentDetails myAgentDetails;

    @Autowired
    public AgentService(AgentRepository agentRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyAgentDetails myAgentDetails) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myAgentDetails = myAgentDetails;
    }


    @Autowired
    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent findAgentByEmailAddress(String email) {
        return agentRepository.findAgentByEmail(email);
    }

    public Agent registerAgent(Agent agentObject) {
        if(!agentRepository.existsByEmail(agentObject.getEmail())) {
            agentObject.setPassword(passwordEncoder.encode(agentObject.getPassword()));
            return agentRepository.save(agentObject);
        } else {
            throw new InformationExistException("An agent with email " + agentObject.getEmail() + " already exists.");
        }
    }

    public Agent findAgentByEmail(String email) {
        return agentRepository.findAgentByEmail(email);
    }

    public ResponseEntity<?> loginAgent(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myAgentDetails = (MyAgentDetails) authentication.getPrincipal();

            final String JWT = jwtUtils.generateJwtToken(myAgentDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Error: username or password is incorrect"));
        }
    }

}
