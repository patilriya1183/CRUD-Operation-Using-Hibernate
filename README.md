# Java Swing CRUD Application with Hibernate and MySQL

This project demonstrates how to integrate a Java Swing GUI application with Hibernate ORM to perform CRUD (Create, Read, Update, Delete) operations on a MySQL database.

## Requirements

1. **Java Development Kit (JDK)**: Make sure you have JDK 8 or above installed.
2. **Maven**: This project uses Maven for dependency management. 
3. **MySQL**: You'll need MySQL installed on your machine along with MySQL Workbench for managing the database.
4. **Hibernate**: Hibernate ORM is used for database operations.

## Features

- **Insert**: Insert data into the MySQL database.
- **Delete**: Delete data from the MySQL database using an ID.
- **Update**: Update an existing record by its ID.
- **View**: Retrieve and display all records in the database.

## Database Setup

1. Open MySQL Workbench and create a new database:
   ```sql
   CREATE DATABASE my_database;


To implement this project, you'll need to break it down into several steps, integrating Java Swing for the GUI and Hibernate for database operations, with MySQL as your database. Here's a detailed guide, along with an outline for the structure of your project and a potential readme file for the GitHub repository.

Project Outline
Java GUI (Swing):

Insert Button: Collects Name and Age from the user through text fields and inserts them into the database.
Delete Button: Takes an ID from the user to delete the corresponding record.
Update Button: Takes an ID and a new Name to update the record.
View Button: Displays all entries from the database in a table or text area.
Hibernate Configuration:

Hibernate will manage the database operations for your GUI application.
You'll need to configure hibernate.cfg.xml with your MySQL database settings.
Database Setup:

MySQL will store the data, and Hibernate will handle the mapping between Java objects and database tables.

My Project
Here is a screenshot of the application: 

Execute Java program that connects to the database using Hibernate. Ensure that the console displays a message indicating a successful connection, such as Connected to the database! :

![a21](https://github.com/user-attachments/assets/17d84ff2-9e59-4c0e-a241-ccb79d4c53e2)

A simple CRUD application GUI allows users to insert, delete, update, and view records with text fields for input, buttons for each action, and a display area for database content. Hibernate handles the SQL operations, and confirmation messages (e.g., "User added successfully!") notify the user of successful actions.


![a22](https://github.com/user-attachments/assets/2851c976-d29a-4132-9d0a-7ea58576bb49)
![a23](https://github.com/user-attachments/assets/9c240e57-12c6-4983-8beb-dc30c76d77a3)


After displaying the success message, use the `View` button to trigger a function that queries the database for updated records and presents the results in a suitable format.

![a24](https://github.com/user-attachments/assets/b6acec59-142e-436a-a6cb-f73c9b786812)


To update data, capture user input and use Hibernate to handle the SQL UPDATE operation via the mapped entity class. Afterward, display a confirmation message like "User updated successfully!" to confirm the operation.

![a25](https://github.com/user-attachments/assets/0d363287-9912-446b-b44c-418c52f469b6)
![a26](https://github.com/user-attachments/assets/311c2b92-c5a2-4ac1-99ed-5de398879bf1)


After displaying the success message, use the `View` button to trigger a function that queries the database for updated records and presents the results in an appropriate format.

![a27](https://github.com/user-attachments/assets/e25f94ab-3278-47e9-ad4b-ce47f6fcffcf)


Capture user input and use Hibernate to handle the SQL DELETE operation via the mapped entity class. After deletion, display a confirmation message like "User deleted successfully!" to confirm the action.

![a28](https://github.com/user-attachments/assets/fd4945d1-57fe-42b4-ae2b-534b13ea25e5)
![a29](https://github.com/user-attachments/assets/6f87bbbc-4b7d-4524-9721-6991d31bc070)


Execute a `SELECT * FROM table_name` query in MySQL to view the final state of the table after all operations, which will display the all records in the table.

![a30](https://github.com/user-attachments/assets/9839fc30-2f71-405b-919f-7a6ff1205146)


Conclusion :
After completing all operations, you will have successfully integrated Hibernate with your Java Swing application to perform CRUD operations with a MySQL database. This integration demonstrates your proficiency in GUI development, Hibernate configuration, and database management. Ensure that the application is thoroughly tested to validate all functionalities, and provide all relevant deliverables, including the SQL file, source code, and detailed documentation, to meet project requirements and showcase your technical expertise.
