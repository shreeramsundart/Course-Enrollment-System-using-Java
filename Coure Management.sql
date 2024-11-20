CREATE DATABASE COURSE_MANAGEMENT;
USE COURSE_MANAGEMENT;

CREATE TABLE STUDENTS (
	student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    phone_number VARCHAR(15),
    registration_date DATETIME DEFAULT CURRENT_TIMESTAMP);
    
    
CREATE TABLE COURSES (
	course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100),
    course_description VARCHAR(255),
    credits INT,
    level_id INT,
    schedule VARCHAR(255),
    max_capacity INT,
    requirements VARCHAR(255),
    FOREIGN KEY(level_id) REFERENCES COURSELEVELS(level_id));

CREATE TABLE REGISTRATIONS (
	registration_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT ,
    course_id INT ,
    registration_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    FOREIGN KEY (student_id) REFERENCES STUDENTS(student_id),
    FOREIGN KEY (course_id) REFERENCES COURSES(course_id));
    
CREATE TABLE COURSELEVELS (
	level_id INT PRIMARY KEY AUTO_INCREMENT,
    level_name VARCHAR(50),
    requirements VARCHAR(255));
    
INSERT INTO COURSELEVELS (level_name, requirements) VALUES 
    ('UNDERGRADUATE', 'Completion of high school education or equivalent'),
    ('POSTGRADUATE', 'Completion of a relevant undergraduate degree'),
    ('DOCTORAL', 'Completion of a relevant postgraduate degree with research experience');
    
CREATE TABLE COURSESCHEDULES (
	schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT,
    day_of_week VARCHAR(20),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (course_id) REFERENCES COURSES(course_id));
    
DELIMITER //
CREATE PROCEDURE removeStudent(IN studentId INT)
BEGIN
    IF (SELECT COUNT(*) FROM STUDENTS WHERE student_id = studentId) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Student not found';
    ELSE
        SET FOREIGN_KEY_CHECKS = 0;
        DELETE FROM REGISTRATIONS WHERE student_id = studentId;
        DELETE FROM STUDENTS WHERE student_id = studentId;
        SET FOREIGN_KEY_CHECKS = 1;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE removeCourse(IN courseId INT)
BEGIN
    IF (SELECT COUNT(*) FROM COURSES WHERE course_id = courseId) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Course not found';
    ELSE
        SET FOREIGN_KEY_CHECKS = 0;
        DELETE FROM REGISTRATIONS WHERE course_id = courseId;
        DELETE FROM COURSESCHEDULES WHERE course_id = courseId;
        DELETE FROM COURSES WHERE course_id = courseId;
        SET FOREIGN_KEY_CHECKS = 1;
    END IF;
END //
DELIMITER ;

