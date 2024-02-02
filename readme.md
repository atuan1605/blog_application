# Personal Blog Application with Java Spring Boot

This README provides comprehensive instructions for setting up, running, and customizing a personal blog interface using Java Spring Boot. This project leverages Spring Boot for backend development, offering a robust, scalable, and easily maintainable platform for blogging.

## Prerequisites
Before you start, ensure you have the following installed on your system:

* JDK 11 or newer
* Maven 3.6 or newer

## Docker Compose 
The `docker-compose.yml` file is configured to orchestrate multiple services: the Spring Boot application (`app`), a PostgreSQL database (`db`), a test database (`test_db`), and a Redis cache (`cache`).

## Application Structure
### The application follows a typical MVC (Model-View-Controller) structure:
* **Model**: Represents the data and business logic of the application. In a blog, models might include `Post`, `Comment`, `Category` and `User`.
* **View**: Responsible for rendering the model data. Although Spring Boot is primarily a backend framework, you can integrate frontend technologies like Thymeleaf, JSP, or connect with a frontend framework like React or Angular.
* **Controller**: Handles incoming HTTP requests, interacts with the model, and returns a response.

## Key Components
**Controllers**: Located in the `controller` package, these components handle HTTP requests and responses.
**Services**: Defined in the `service` package, services contain business logic and interact with the repository layer.
**Repositories**: Situated in the `repository` package, these interfaces extend `JpaRepository` or `CrudRepository` for data access operations.
**Entities**: Located in the `model` or `entity` package, these classes are annotated with JPA annotations to represent tables in your database.

## Database Configuration
Spring Boot supports various relational and NoSQL databases. Configure your database connection in the `application.properties` or `application.yml` file within the `src/main/resources` directory:

```properties
# Example MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:5432/yourDatabase
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.datasource.driverClassName = org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Customizing Your Blog
You can customize your blog by adding new entities, controllers, and services. For example:

1) **Entity**: Create a `Post` class in the `model` or `entity` package with annotations to define it as a JPA entity.
2) **Repository**: Define a PostRepository interface that extends JpaRepository.
3) **Service**: Implement a PostService class in the service package to handle business logic related to posts.
4) **Controller**: Create a PostController class in the controller package to manage HTTP requests related to blog posts.

## SYSTEM FUNCTIONS
1) User Management:
* Functionality: Account creation, updating user profiles, changing passwords, and account deletion
* Role: Both 'users', 'admins', and 'authors' can manage their own profiles. However, only 'admins' have the authority to delete accounts and assign roles to users and authors. 
2) Content Creation:
* Functionality: Writing, editing, deleting articles, uploading images, and categorizing articles
* Role: 'Users' can only view articles, 'authors' can create articles but cannot edit or delete others' articles, whereas 'admins' can manage all users' articles.
3) Comments:
* Functionality: Users can create, edit, or delete their own comments
* Role: All roles can comment and manage their own comments. However, admins can delete comments made by 'users' and 'authors'.
4) Category Management:
* Functionality: Creating and managing categories assigned to articles
* Role: Restricted to admins only to ensure controlled management of categories.
5) Role Management:
* Functionality: Assigning and defining roles and permissions
* Role: Reserved for admins to prevent inappropriate permission assignments.
6) User Activity and History Logging:
* Functionality: Logging user actions for easy querying in case of changes

## Roles:

1) User:
Can create, read, update, and delete their own comments.
Not allowed to create/edit/delete posts
Can view posts and comments from other users
Cannot view/edit user list
2) Author:
Can create, read, update, and delete their own comments.
Allowed to create/edit/delete posts
Can view posts and comments from other users
Cannot view/edit user list
3) Admin:
Can create, read, update, and delete their own comments as well as those of users and authors
Allowed to create/edit/delete posts including those of users and authors
Can view/edit posts and comments from other users
Can view/edit the user list
Can query the action history of users and authors