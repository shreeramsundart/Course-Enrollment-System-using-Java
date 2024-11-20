# Course Enrollment System

This project is a **Course Enrollment System** built using **Java** and **MySQL**. It was developed as a part of the **JAVA LEAP workshop** conducted by **Sri Eshwar College of Engineering, Coimbatore**. The project demonstrates the integration of **core Java concepts** with **DBMS techniques** to create a functional and user-friendly application for course management and enrollment.

---

## Key Features

### Student Functionalities
- **Course Browsing**: Students can view available courses with details like course name, description, and prerequisites.
- **Student Registration**: New students can register by providing their details, which are securely stored in the database.
- **Course Enrollment**: Registered students can enroll in their desired courses.
- **View Course Details**: Students can view the details of courses they are enrolled in.
- **Course Progress Management**: Tracks progress made by students for each enrolled course.

### Admin Functionalities
- **Add Courses**: Admins can create and manage courses by providing essential details.
- **Update Course Details**: Admins can edit existing course information as needed.
- **View Registered Students**: Admins can access details of all registered students.

---

## Technologies Used
- **Java**: The primary programming language for backend logic and data manipulation.
- **MySQL**: Database used to store and manage data for courses, students, and enrollments.
- **JDBC (Java Database Connectivity)**: Facilitates communication between the Java application and MySQL database.

---

## Key Concepts Implemented

### Core Java Concepts
- **Object-Oriented Programming (OOP)**: Classes like `Student`, `Course`, and `Enrollment` demonstrate OOP principles such as encapsulation, inheritance, and polymorphism.
- **Exception Handling**: Ensures robust error handling for invalid inputs, database issues, or enrollment conflicts.
- **File Handling**: Logs system activities for audit purposes.

### Database Management System (DBMS)
- **Relational Database**: MySQL tables represent entities like `Students`, `Courses`, and `Enrollments`.
- **CRUD Operations**: Supports Create, Read, Update, and Delete operations on database records.
- **Data Integrity**: Enforced via constraints like primary keys, foreign keys, and unique indices.

### JDBC
- Establishes secure and efficient connections between the Java application and MySQL database.
- Executes SQL queries and handles database transactions seamlessly.

### User Interface
- **Console-Based Interface**: Simplifies interaction with menu-driven navigation.

---

## Installation and Setup

### Prerequisites
1. **Java Development Kit (JDK)**: Ensure JDK 8 or later is installed.
2. **MySQL Server**: Install and configure MySQL server on your machine.
3. **IDE**: (Optional) Use IntelliJ IDEA, Eclipse, or any Java IDE for development and testing.

### Steps to Run the Project
1. Clone or download the project repository.
2. **Set up the database**:
   - Create a MySQL database for the system.
3. **Update database configuration**:
   - Modify the `Database_Connection` class to include your database credentials (username, password).
4. **Compile and run the project**:
   - Compile Java files using `javac`.
   - Run the main class `CourseManagementApp`.

---

## Acknowledgments

This project is the outcome of the **JAVA LEAP Workshop** hosted by **Sri Eshwar College of Engineering, Coimbatore**. The workshop aimed to equip participants with hands-on experience in developing real-world applications using **Java** and **MySQL**.

---

## License

This project is licensed under the **MIT License**.

---
