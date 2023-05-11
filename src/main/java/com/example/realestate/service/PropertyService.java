package com.example.realestate.service;

import com.example.realestate.exception.InformationExistException;
import com.example.realestate.exception.InformationNotFoundException;
import com.example.realestate.model.Agent;
import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.security.MyAgentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;

    @Autowired
    public void setPropertyRepository(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public static Agent getCurrentLoggedInAgent(){
        MyAgentDetails agentDetails = (MyAgentDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return agentDetails.getAgent();
    }

    /**
     * Gets a list of properties
     * @return a list of properties
     */
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Gets property by property id
     * @param propertyId we are searching for
     * @return property based on id
     */
    public Property getProperty(Long propertyId) {
        return propertyRepository.findById(propertyId).orElse(null);
    }

    /**
     * Creates property object
     * @param propertyObject property object being added
     * @return the added property object
     * @throws InformationExistException if property already exists
     */

    public Optional<Property> createProperty(Property propertyObject) {
//        Optional<Property> property = propertyRepository.findById(PropertyService.getCurrentLoggedInAgent().getId());
        Optional<Property> property = propertyRepository.findByAddress(propertyObject.getAddress());
        if (property.isPresent()) {
            throw new InformationExistException("Property " + property.get().getAddress() + " already exists");
        } else {
            propertyObject.setAgent(PropertyService.getCurrentLoggedInAgent());
            return Optional.of(propertyRepository.save(propertyObject));
        }
    }

    /**
     * Updates property object
     * @param propertyId property id we are updating
     * @param propertyObject property object we are updating to
     * @return updated property
     * @throws InformationNotFoundException if property address not found
     */
    public Optional<Property> updateProperty(Long propertyId, Property propertyObject) {
        Optional<Property> property = propertyRepository.findByIdAndAgentId(propertyId, PropertyService.getCurrentLoggedInAgent().getId());
        if(property.isPresent()){
            property.get().setPrice(propertyObject.getPrice());
            property.get().setSize(propertyObject.getSize());
            return Optional.of(propertyRepository.save(property.get()));
        } else {
            throw new InformationNotFoundException("Property with address: " + propertyObject.getAddress() + " doesn't exist");
        }
    }

    /**
     * Deletes property by property id
     * @param propertyId property id we are deleting
     * @return a String stating whether it was successfully deleted if property id exists
     * @throws InformationNotFoundException if property id does not exist
     */
    public String deleteProperty(Long propertyId) {
        Optional<Property> property = propertyRepository.findByIdAndAgentId(propertyId, PropertyService.getCurrentLoggedInAgent().getId());
        if(property.isPresent()) {
            propertyRepository.deleteById(propertyId);
            return "Property with id " + propertyId + " was deleted";
        } else {
            throw new InformationNotFoundException("Property with id: " + propertyId + " doesn't exist");
        }
    }

    public List<Property> getPropertiesWithFilter(String size, String price) {
        List<Property> bringList = propertyRepository.findAll();

        String[] sizeParts = size.split("-");
        int lowSize = Integer.parseInt(sizeParts[0]);
        int highSize = Integer.parseInt(sizeParts[1]);

        String[] priceParts = size.split("-");
        int lowPrice = Integer.parseInt(priceParts[0]);
        int highPrice = Integer.parseInt(priceParts[1]);

        return bringList.stream().filter(prop -> (prop.getSize() >= lowSize && prop.getSize() <= highSize) && (prop.getPrice() >= lowPrice && prop.getPrice() <= highPrice)).collect(Collectors.toList());
    }
}
