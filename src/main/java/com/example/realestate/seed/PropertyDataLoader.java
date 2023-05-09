package com.example.realestate.seed;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PropertyDataLoader implements CommandLineRunner {

    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (propertyRepository.count() == 0) {
            Property property1 = new Property(1L, "123 Programmer Ln", 1500.00, 900);
            Property property2 = new Property(2L, "Sesame St", 800, 400);
            Property property3 = new Property(3L, "Spring Boot Cr", 2000, 1200);
            Property property4 = new Property(4L, "Assured Ave", 2300, 1000);
            Property property5 = new Property(5L, "Refactor Ln", 2200, 700);

            propertyRepository.save(property1);
            propertyRepository.save(property2);
            propertyRepository.save(property3);
            propertyRepository.save(property4);
            propertyRepository.save(property5);
        }
    }
}
