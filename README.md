# Real-Estate API

## Overview
Real Estate Management System: A RestAPI for a real estate management system that allows agents to create, delete, and update property listings, manage property sales, and allow all users to view properties listed.

<br>

## Tools and Technologies
* Java
* IDE - IntelliJ
* Spring Boot
* JPA
* JWT
* Postman
* Cucumber Spring Integration

## User Stories
Bronze:
- As an agent, I should be able to login into my account to use the application and get access to property listings.
- As an agent, I should be able to add a property listing.
- As an agent, I should be able to update a property listing.
- As an agent, I should be able to delete a property listing.
- As an agent, I should be able to sell properties.

Silver:
- As a customer, I should be able to view all property listings.
- As a customer, I should be able to filter properties based on price, size.
- As a customer, I should be able to find properties listed by a specific agent.

Gold:
- As an agent, I should be able to add pictures of the property listing.
- As a customer, I should be able to see pictures of the property listing.

<br>

## ERD Diagram

<img src="erd-diagram.png" style="height: 35rem;">

<br>

## API Endpoints

| Request Type | URL                   | Functionality              | Access | 
|--------------|-----------------------|----------------------------|---------|
| POST         | /users/login/         | User login               	 | Public |
| POST         | /users/register/      | User registration        	 | Public |
| GET          | /properties/          | Properties 		              | Public |
| GET          | /properties/{id}/     | Property           	       | Public |
| POST         | /properties/          | Create Property          	 | Private |
| PUT          | /properties/{id}/     | Update Property         	  | Private |
| DELETE       | /properties/{id}/   	 | Delete Property         	  | Private |
| GET        | /sales/               | Get all sales              | Private |
| GET        | /sales/{id}/          | Get sale by id             | Private |
| POST        | /sales/               | Add sale                   | Private |