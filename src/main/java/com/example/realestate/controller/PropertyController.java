package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class PropertyController {

    private PropertyService propertyService;

    @Autowired
    public void setPropertyService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Calls on getProperties() through propertyService
     * @return List of properties
     */
    @GetMapping(path = "/properties/")
    public List<Property> getProperties() {
        return propertyService.getProperties();
    }

    @GetMapping(path = "/properties/{propertyId}/")
    public Property getProperty(@PathVariable Long propertyId) {
        return propertyService.getProperty(propertyId);
    }
}
