Product API

Lightweight Java service to fetch product data from MySQL.

Endpoints:

GET/products → Get all products

GET/products/{id} → Get specific product

Requirements: Java 21, MySQL

Run: Start server, call endpoints

Data: Returns clean JSON

Errors: Handles missing/invalid requests
API layer handles HTTP requests

Service layer processes business logic

Database layer manages MySQL queries
