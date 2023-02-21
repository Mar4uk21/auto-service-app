![img.png](images/Intro.jpg)
# 🚘Car-Service🚘
## Project description:
___
This project is written using the REST-API of the server, which accepts HTTP requests, stores data in a database (DBMS - PostgreSQL) and
processes them. It's application for auto-service.You can add clients with their cars, create orders with services and products that you need.
Also, you can calculate order price, change order status, calculate master salary according to their completed orders too.
And of course we have discounts system that based on client`s orders quantity. 
## 🎓Features🎓:
___
| Controller      |                                                  ENDPOINT                                                   |  
|-----------------|:-----------------------------------------------------------------------------------------------------------:|
| `Masters`       |                  POST: `/masters`<br/>PUT:`/{id}` <br/> GET:`/{id}/orders`, `/{id}/salary`                  |
| `Cars`          |                                       POST: `/cars`,<br/>PUT: `/{id}`                                       |
| `Car-Owners`    |                     GET: `/{id}/orders`,<br/>POST:`/car-owners` <br/>PUT: `/{id}`<br/>                      |
| `Orders`        | POST: `/orders`,`/{orderId}/{productId}`<br/>PUT:`/{id}`, `/{id}/{statusCode}`<br/>  GET:`/{id}/totalPrice` |  
| `Type-Services` |                           POST: `/type-services`<br/>PUT:`/{id}`, `/{id}/change`                            |
| `Products`      |                                      POST: `/products`<br/>PUT:`/{id}`                                      |
#### Or you can you Swagger and read more information about this endpoints: http://localhost:8080/swagger-ui.html#/ (if you use docker use this link http://localhost:6868/swagger-ui.html#/)
## 💻Technologies💻
___
| **Technology** | **Version** |
|----------------|-------------|
| JDK            | 17          |
| Maven          | 4.0.0       |
| Spring Boot    | 2.5.9       |
| Swagger UI     | 2.7.0       |
| Postgres       | 15          |
| Liquibase      | -           |

## 💼Instructions for launching the project 💼
___
1. Clone the project
2. Download Docker🧩
3. Build project with ```mvn clean package -DskipTests```
4. Write this command to console✒️
```bash
    docker-compose up
```

5. 🥳*Enjoy*🥳
