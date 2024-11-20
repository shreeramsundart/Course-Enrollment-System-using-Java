package main;

import java.io.IOException;
import java.sql.SQLException;

import controller.CourseController;
import exception.CourseNotFoundException;
import exception.StudentNotFoundException;

public class CourseManagementApp {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException, StudentNotFoundException, CourseNotFoundException {
		CourseController cc = new CourseController();
		cc.start();

	}

}
