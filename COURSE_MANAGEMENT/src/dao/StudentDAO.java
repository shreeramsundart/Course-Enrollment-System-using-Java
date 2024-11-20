package dao;

import java.io.IOException;
import java.sql.SQLException;

import exception.StudentNotFoundException;
import model.Student;

public interface StudentDAO {

	void addStudent(Student student) throws SQLException, IOException;

	void removeStudent(int studentId) throws StudentNotFoundException, IOException;

}
