package com.example.realestate.service;

import com.example.realestate.exception.InformationExistException;
import com.example.realestate.exception.InformationNotFoundException;
import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;

    @Autowired
    public void setPropertyRepository(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    public Property getProperty(Long propertyId) {
        return propertyRepository.findById(propertyId).orElse(null);
    }

    public Optional<Property> createProperty(Property propertyObject) {
        Optional<Property> property = propertyRepository.findByAddress(propertyObject.getAddress());
        if (property.isPresent()) {
            throw new InformationExistException("Property " + property.get().getAddress() + " already exists");
        } else {
            return Optional.of(propertyRepository.save(propertyObject));
        }
    }

    public Optional<Property> updateProperty(Long propertyId, Property propertyObject) {
        Optional<Property> property = propertyRepository.findByAddress(propertyObject.getAddress());
        if(property.isPresent()){
            property.get().setPrice(propertyObject.getPrice());
            property.get().setSize(propertyObject.getSize());
            return Optional.of(propertyRepository.save(property.get()));
        } else {
            throw new InformationNotFoundException("Property with address: " + propertyObject.getAddress() + " doesn't exist");
        }
    }

}
