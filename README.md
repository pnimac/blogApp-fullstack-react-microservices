## Description

This is a simple blog application demostratating microservice orchestration by using an API Gateway, a Configuration server, an Eureka Discovery server and microservices for different functional components like User Management, Posts, Comments etc..  Thymeleaf templates are used for Front-end UI.

## Architecture
![Microservice Orchestration Architecure](images/architecture.png)

## Feature

1. Home Page: Displays a list of all posts by all users, ordered by the most recent.
2. Signup Page: Collects basic information to register a new user.
3. Login Page: Authenticates users via JWT token authentication, enabling them to comment on existing posts and create new posts.
4. Add Post Page: Allows users to create a new post with a character limit of 126.
5. Add Comment Page: Enables users to comment on posts posted by themselves or other users, with a character limit of 126.
6. Content Management: Users can delete their own posts and comments.

## Components

1. **Config Server** : Centralizes configuration for all microservices.
2. **Discovery Server** : Manages the registration and discovery of microservices.
3. **API Gateway** : Routes requests to appropriate microservices and provides load balancing, authentication, and other gateway functionalities.
4. **User Service** : Separate services for user management. Manages user registration and authentication.
5. **Post Service** : Separate services for post management. Manages blog posts, including creation, retrieval and deletion.
6. **Comment Service** : Separate services for comment management.
7. **Authorization Service** : Separate services responsible for handling authentication and issuing tokens. 

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring MVC
- Thymeleaf
- MySQL
- Maven
- Docker

## Prerequisite


## Testing


