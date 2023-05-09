package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class PropertyController {
    private PropertyRepository propertyRepository;

    @Autowired
    public void setPropertyRepository(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping(path = "/properties/")
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }
}
