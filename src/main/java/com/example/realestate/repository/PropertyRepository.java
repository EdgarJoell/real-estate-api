package com.example.realestate.repository;

import com.example.realestate.model.Agent;
import com.example.realestate.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findById(Long id);

    Optional<Property> findByAddress(String address);

    Optional<Property> findByIdAndAgentId(Long id, Long agentId);
}
