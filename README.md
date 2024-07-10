# Simple E-Store Checkout App

Demostrate microservice orchestration for a simple estore checkout app using a gateway configuration, a config server, and a discovery server.

# Usecase
![Microservice Orchestration Architecure](images/msorchestration.png)

## Architecture

- **API Gateway**: Routes requests to appropriate microservices and provides load balancing, authentication, and other gateway functionalities.
- **Config Server**: Centralizes configuration for all microservices.
- **Discovery Server**: Manages the registration and discovery of microservices.
- **Microservices**: Separate services for user management, product management, and order management.

## Components

1. **Config Server**
2. **Discovery Server**
3. **API Gateway**
4. **User Service**
5. **Product Service**
6. **Order Service**
