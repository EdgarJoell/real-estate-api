package com.example.realestate.seed;

import com.example.realestate.model.Agent;
import com.example.realestate.model.Property;
import com.example.realestate.model.Sale;
import com.example.realestate.repository.AgentRepository;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.repository.SaleRepository;
import com.example.realestate.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    AgentService agentService;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (propertyRepository.count() == 0 || saleRepository.count() == 0 || agentRepository.count() == 0) {
            Agent agent1 = new Agent(1L, "Bob", "mail@gmail.com", "123456", "999-999-9999");
            Agent agent2 = new Agent(2L, "Tom", "mail1@gmail.com", "123456", "999-999-9988");
            Agent agent3 = new Agent(3L, "Ann", "mail2@gmail.com", "123456", "999-999-9989");
            agentService.registerAgent(agent1);
            agentService.registerAgent(agent2);
            agentService.registerAgent(agent3);

            Property property1 = new Property(1L, "123 Programmer Ln", 1500.00, 900);
            Property property2 = new Property(2L, "Sesame St", 800, 400);
            Property property3 = new Property(3L, "Spring Boot Cr", 2000, 1200);
            Property property4 = new Property(4L, "Assured Ave", 2300, 1000);
            Property property5 = new Property(5L, "Refactor Ln", 2200, 700);

            property1.setAgent(agent1);
            property2.setAgent(agent1);
            property3.setAgent(agent2);
            property4.setAgent(agent3);
            property5.setAgent(agent3);

            propertyRepository.save(property1);
            propertyRepository.save(property2);
            propertyRepository.save(property3);
            propertyRepository.save(property4);
            propertyRepository.save(property5);

            Sale sale1 = new Sale(1L,  1500.00, new Date(2023, 05, 01), property1);
            Sale sale2 = new Sale(2L, 1000.00 , new Date(2023, 05, 02), property2);
            Sale sale3 = new Sale(3L, 2000, new Date(2023, 05, 03), property3);

            sale1.setAgent(agent1);
            sale2.setAgent(agent1);
            sale3.setAgent(agent2);

            saleRepository.save(sale1);
            saleRepository.save(sale2);
            saleRepository.save(sale3);
        }
    }
}
