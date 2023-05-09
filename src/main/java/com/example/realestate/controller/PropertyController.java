package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class PropertyController {
    @Autowired
    private PropertyRepository propertyRepository;

    private PropertyService propertyService;

    @Autowired
    public void setPropertyRepository(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     * Calls on getProperties() through propertyService
     * @return List of properties
     */
    @GetMapping(path = "/properties/")
    public List<Property> getProperties() {
        return propertyService.getProperties();
    }
}
