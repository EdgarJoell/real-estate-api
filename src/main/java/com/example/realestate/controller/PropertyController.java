package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/properties/")
    public Optional<Property> createProperty(@RequestBody Property propertyObject) {
        return propertyService.createProperty(propertyObject);
    }
}
