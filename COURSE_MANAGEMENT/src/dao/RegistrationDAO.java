package dao;

import exception.CourseNotFoundException;
import exception.StudentNotFoundException;

public interface RegistrationDAO {

	void enrollCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException;

	void dropCourse(int registrationID);

}
