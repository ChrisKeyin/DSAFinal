Warehouse Inventory System API
Overview

This project is a Spring Boot REST API for managing a warehouse inventory system. It allows users to create and manage products,
customers, and orders, and includes both a manual sorting algorithm and a BST for handling order priority.

The system demonstrates:

Object-oriented design using JPA entities,
RESTful API development with Spring Boot,
A manually implemented sorting algorithm,
A custom Binary Search Tree for priority-based operations,

Technologies Used

Java,
Spring Boot,
Spring Data JPA,
MySQL,
Postman,
JUnit,
GitHub,

Features

Product Management,
Create products,
View all products,
Sort products by price,
Sort products by stock,
Customer Management,
Create customers,
View all customers,
Order Management,
Create orders linked to customers,
Add multiple products to an order using order items,
Validate input (priority level, product IDs, etc.),
Binary Search Tree (BST),
Insert orders into a BST based on priority,
Retrieve orders in sorted priority order (inorder traversal),
Find highest priority order,
Find lowest priority order,
Sorting Algorithm,
Manual implementation of insertion sort,
Used to sort products by price and stock,
Unit Testing,
Tests for BST traversal and highest priority logic,
Test for product sorting functionality,

How to Run the Project

1. Clone the Repository
   git clone https://github.com/ChrisKeyin/DSAFinal
   cd warehouse-inventory-system
2. Open in IntelliJ
   Open IntelliJ IDEA
   Select Open Project
   Choose the project folder
3. Configure Database

Make sure MySQL is running and update your application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/warehouse_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run the Application
   Run the main Spring Boot application class
   The API will start on:
   http://localhost:8080
   How to Use the API (Postman)
   Create Product

POST /products

{
"name": "Laptop",
"price": 1200.00,
"stock": 10
}
Get All Products

GET /products

Sort Products

GET /products/sorted?by=price
GET /products/sorted?by=stock

Create Customer

POST /customers

{
"name": "John Doe",
"email": "john@example.com"
}
Create Order (with items)

POST /orders

{
"orderDate": "2026-04-18",
"priorityLevel": 5,
"customerId": 1,
"items": [
{
"productId": 1,
"quantity": 2
},
{
"productId": 2,
"quantity": 1
}
]
}
Load Orders into BST

POST /orders/add-to-priority-tree

BST Operations

GET /orders/priority/inorder
GET /orders/priority/highest
GET /orders/priority/lowest

Design Notes
Higher number values represent higher priority in the BST,
The BST is stored in memory and must be rebuilt after application restart,
Sorting is handled manually in the service layer to meet assignment requirements,
Order items are linked using JPA relationships between Order and Product,


Theory Questions Binary Search Tree
1. Why does an inorder traversal of a BST return sorted results?

Inorder travel visits the left child first, then the node, then the right child. 
In a BST, Small on the left, large on the right. Because of that, 
visiting the nodes in inorder go's from left to right regularly.

2. What happens to the tree if you insert values in order (1,2,3,4,5)? How does this affect performance?

If values get inserted in order, the tree becomes unbalanced begins to look like a leaning linked list.
This loses the main advantage of branching well, resulting in less efficient function.
Performance gets worse because operations can move from being fast to taking a lot of time.

3. Where would you place duplicate priority values in your tree? Explain your choice.

I would place duplicate values on the right side. This keeps the insertion logic consistent and makes the tree easier to manage. 
Using one clear rule for duplicates prevents confusion and ensures the tree structure stays predictable.

Sorting Algorithm
4. Explain how your sorting algorithm works step-by-step using a small example.

I used insertion sort. It works by treating the first item as already sorted, 
then taking the next item and placing it into the correct position in the sorted part of the list.

For example, with prices [50, 20, 40]:

Start with 50 as sorted, Compare 20 to 50, and move 50 right. Insert 20 before it → [20, 50, 40]. Take 40
Compare 40 to 50, and move 50 right. Insert 40 between 20 and 50 → [20, 40, 50]

That process repeats until all items are in proper order.

5. What is the time complexity of your algorithm?

The worst-case time complexity of insertion sort is O(n²) because each item may need to be compared with many earlier items.
In the best case, when the list is already mostly sorted, it performs much better at O(n).

6. When would your sorting algorithm perform well?

Insertion sort performs well on small lists or lists that are already sorted or nearly sorted. 
It is simple, easy to implement, and works well when the dataset is not very large.

7. Why is your sorting algorithm ideal or not ideal for very large datasets?

Insertion sort is not ideal for very large datasets because the more items to compare or shift, the slower it can become.
It is better for smaller datasets, where more simple is more useful and the performance cost is much less.

System Design
8. Why might you choose to sort data in your application instead of the database?

You might choose to sort in the application when the goal is to practice or demonstrate an algorithm, like in this project. 
It also gives more control over how the sorting is implemented. In this system, sorting in the application was important because 
the assignment specifically required a manual sorting algorithm.

9. What is one advantage of using a BST in this system?

One advantage of using a BST is that it keeps priority values organized in a structure that makes it easy to find the lowest 
and highest priority orders. It also allows inorder traversal to return the orders in sorted priority order.

10. What is one limitation of your current design?

One limitation of the current design is that the BST is stored in memory only. If the application restarts, the tree is cleared 
and the orders must be added back into it again. This means the BST is not permanent like the database data.


AI Usage:

I used AI to help troubleshoot a plethora of error codes I encountered while building this project such as, multiple infinite recursive 
Loop errors with the database. As while as helping me keep the flow of development straight by helping me organized what areas to
focus my efforts on first. It also helped me diagnose several errors that resulted in failed unit tests.