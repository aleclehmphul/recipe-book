# Recipe Management Application

## Description

This Java Spring Boot application provides a robust API for managing recipes within a recipe book. It allows users to create, retrieve, update, and delete recipes, making it an ideal backend for culinary websites or mobile apps. The application is built with Spring Boot, utilizing RESTful principles to ensure easy integration and scalability.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 17 or later
- Maven 3.6 or later
- PostgreSQL version 16 or later

## Installation

Follow these steps to get your development environment running:

1. **Clone the repository**
   ```bash
   git clone https://github.com/aleclehmphul/recipe-book.git
   cd recipe-book
   ```
2. **Configure PostgreSQL database and Flyway database migration credentials**

Please make sure to create a PostgreSQL database called ```recipebook``` and update `src/main/resources/application.yml` with your database credentials and URL.

   ```bash
   app:
     datasource:
       main:
         driver-class-name: org.postgresql.Driver
         jdbc-url: jdbc:postgresql://localhost:5432/recipebook
         username: <your_username>
         password: <your_password>
         pool-size: 30

   spring:
     flyway:
       url: jdbc:postgresql://localhost:5432/recipebook
       user: <your_username>
       password: <yout_password>
       enabled: true
  ```
  3. **Run the application**

Use Maven to run the application.

   ```bash
   mvn spring-boot:run
   ```

The application will start running at http://localhost:8080.

## Usage

### Creating a Recipe
```http
POST /api/v1/recipes
Content-Type: application/json

{
    "title": "Classic French Onion Soup",
    "category": 1,
    "ingredients": [
        {
            "name": "butter",
            "amount": 0.25,
            "measuring_unit": "c"
        },
        {
            "name": "large sliced onions",
            "amount": 3,
            "measuring_unit": "N/A"
        },
        {
            "name": "dry white wine",
            "amount": 1,
            "measuring_unit": "c"
        },
        {
            "name": "beef broth",
            "amount": 3,
            "measuring_unit": "cans (14oz)"
        },
        {
            "name": "dried thyme",
            "amount": 0.5,
            "measuring_unit": "t"
        },
        {
            "name": "salt",
            "amount": 0.5,
            "measuring_unit": "t"
        },
        {
            "name": "worcestershire sauce",
            "amount": 1,
            "measuring_unit": "t"
        },
        {
            "name": "french bread",
            "amount": 1,
            "measuring_unit": "loaf"
        },
        {
            "name": "shredded swiss cheese",
            "amount": 4,
            "measuring_unit": "oz"
        }
    ],
    "directions": [
        {
            "description": "Melt butter + saute onions til they are soft and brown",
            "taskNumber": 1
        },
        {
            "description": "Stir in wine",
            "taskNumber": 2
        },
        {
            "description": "Combine wine, onion mixture w/ broth, thyme, salt, and worcestershire sauce",
            "taskNumber": 3
        },
        {
            "description": "Cook in slow cooker or on stove on low heat for 4 - 4.5 hours",
            "taskNumber": 4
        }
    ],
    "note": ""
}
```

### Retrieving All Recipes
```http
GET /api/v1/recipes
```

### Retrieving a Recipe
```http
GET /api/v1/recipes/{id}
```

### Updating a Recipe
```http
PUT /api/v1/recipes/{id}
Content-Type: application/json

{
    "title": "Classic French Onion Soup 2.0",
    "category": 1,
    "ingredients": [
        {
            "name": "new ingredient",
            "amount": 2,
            "measuring_unit": "N/A"
        }
        {
            "name": "butter",
            "amount": 0.25,
            "measuring_unit": "c"
        },
        {
            "name": "large sliced onions",
            "amount": 3,
            "measuring_unit": "N/A"
        },
        {
            "name": "dry white wine",
            "amount": 1,
            "measuring_unit": "c"
        },
        {
            "name": "beef broth",
            "amount": 3,
            "measuring_unit": "cans (14oz)"
        },
        {
            "name": "dried thyme",
            "amount": 0.5,
            "measuring_unit": "t"
        },
        {
            "name": "salt",
            "amount": 0.5,
            "measuring_unit": "t"
        },
        {
            "name": "worcestershire sauce",
            "amount": 1,
            "measuring_unit": "t"
        },
        {
            "name": "french bread",
            "amount": 1,
            "measuring_unit": "loaf"
        },
        {
            "name": "shredded swiss cheese",
            "amount": 4,
            "measuring_unit": "oz"
        },
    ],
    "directions": [
        {
            "description": "Updated description",
            "taskNumber": 1
        },
        {
            "description": "Stir in wine",
            "taskNumber": 2
        },
        {
            "description": "Combine wine, onion mixture w/ broth, thyme, salt, and worcestershire sauce",
            "taskNumber": 3
        },
        {
            "description": "Cook in slow cooker or on stove on low heat for 4 - 4.5 hours",
            "taskNumber": 4
        }
    ],
    "note": "This now has a note!"
}
```

### Deleting a Recipe
```http
DELETE /api/v1/recipes/{id}
```

---
**_Note:_**

{id} corresponds to the recipe id (from the recipe table) stored in the database.

---

## Database and ERD

The database initialization code can be found inside ```src/main/resources/db/migration/V1__InitTables.sql``` file. This SQL file is automatically executed when the application is started.

Below is the Entity Relationship Diagram for this application.

![Recipe_ER_Diagram](https://github.com/aleclehmphul/recipe-book/assets/61329825/257d7708-5898-4b44-9aaf-9c3d72cbfead)



## License
This project is licensed under the MIT License.
