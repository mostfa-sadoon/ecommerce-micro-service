# 🛍️ E-commerce Microservices Project

This project is a simple e-commerce system built with **Spring Boot**, **MySQL**, **RabbitMQ**, and **Docker Compose**.  
It demonstrates communication between microservices (User, Product, Order, and Wallet services) using **RabbitMQ**.

---

## 🧩 Microservices Overview
- **User Service** — Handles user registration and management.  
- **Product Service** — Manages product catalog and stock.  
- **Order Service** — Handles order creation and communication with wallet and product services.  
- **Wallet Service** — Manages user wallet balance and transactions.  
- **RabbitMQ** — Used for event-driven communication between services.

---

## 🐳 Docker Setup

### 1️⃣ Create Docker Network
All containers communicate using a shared Docker network:
```bash
docker network create ecommerce-network
```

---

### 2️⃣ Run RabbitMQ Container
Run RabbitMQ with management UI:
```bash
docker run -d   --name rabbitmq   --hostname rabbitmq   --network ecommerce-network   -p 5672:5672   -p 15672:15672   rabbitmq:3-management
```

After running this command, open [http://localhost:15672](http://localhost:15672)  
Default credentials:
```
Username: guest
Password: guest
```

---

### 3️⃣ Run MySQL Containers
Each service that requires a database can have its own MySQL container, for example:
```bash
docker run -d   --name users_db   --network ecommerce-network   -e MYSQL_ROOT_PASSWORD=root   -e MYSQL_DATABASE=ecommerce-users   -p 3307:3306   mysql:8.0
```

You can repeat this for product, order, and wallet databases (just change container name and port).

---

### 4️⃣ Run Microservices
Build and run each service (inside its directory):
```bash
./mvnw clean package -DskipTests
docker build -t user-service .
docker run -d --name user-service --network ecommerce-network -p 8081:8081 user-service
```

Repeat for:
- product-service (port 8082)
- wallet-service (port 8083)
- order-service (port 8084)

---

## ⚙️ Example RabbitMQ Queues
Each service communicates via queues such as:
- `order_created_product_queue`
- `order_created_wallet_queue`
- `wallet_update_status_queue`
- `product_update_stock_queue`

---

## 🧠 Useful Commands
View running containers:
```bash
docker ps
```

Check network connectivity:
```bash
docker network inspect ecommerce-network
```

Remove containers:
```bash
docker rm -f rabbitmq users_db
```

Remove network:
```bash
docker network rm ecommerce-network
```

---

## ✅ Summary
After completing these steps:
- RabbitMQ is accessible at [http://localhost:15672](http://localhost:15672)  
- Each microservice runs inside the same network (`ecommerce-network`)
- All services can communicate seamlessly through RabbitMQ events.

---
