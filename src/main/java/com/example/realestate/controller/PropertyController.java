package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
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

    /**
     * Calls on getProperty() from PropertyService
     * @param propertyId property id we are searching for
     * @return a property based on property id
     */
    @GetMapping(path = "/properties/{propertyId}/")
    public Property getProperty(@PathVariable Long propertyId) {
        return propertyService.getProperty(propertyId);
    }

    /**
     * Calls on createProperty() from PropertyService and returns status 201 if successful
     * @param propertyObject property we are adding
     * @return a property object that we have added
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/properties/")
    public Optional<Property> createProperty(@RequestBody Property propertyObject) {
        return propertyService.createProperty(propertyObject);
    }

    /**
     * Calls on updateProperty() from PropertyService
     * @param propertyId property id we are searching for
     * @param propertyObject property object we are updating
     * @return an updated property object
     */
    @PutMapping(path = "/properties/{propertyId}/")
    public Optional<Property> updateProperty(@PathVariable Long propertyId, @RequestBody Property propertyObject) {
        return propertyService.updateProperty(propertyId,propertyObject);
    }

    /**
     * Calls on updateProperty() from PropertyService
     * @param propertyId property id we are searching for
     * @return a String stating whether the deletion was successful, throws an exception otherwise.
     */
    @DeleteMapping(path = "/properties/{propertyId}")
    public String deleteProperty(@PathVariable Long propertyId) {
        return propertyService.deleteProperty(propertyId);
    }

    /**
     * Calls on getPropertiesWithFilter() from PropertyService
     * @param size property size we are searching for
     * @param price property price we are searching for
     * @return a list of properties that match size and price
     */
    @GetMapping(path = "/properties/size={size}/price={price}/")
    public List<Property> getPropertiesWithFilter(@PathVariable String size, @PathVariable String price) {
        return propertyService.getPropertiesWithFilter(size, price);
    }

    @GetMapping(path ="/properties/agent/{agentId}/")
    public List<Property> getPropertyByAgentId(@PathVariable Long agentId) {
        return propertyService.getPropertyByAgentId(agentId);
    }
}
