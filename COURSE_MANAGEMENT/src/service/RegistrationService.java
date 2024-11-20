package service;

import dao.RegistrationDAO;
import dao.RegistrationDAOimpl;
import exception.CourseNotFoundException;
import exception.StudentNotFoundException;

public class RegistrationService {
	
	private RegistrationDAO registrationDAO;

	public RegistrationService() {
		this.registrationDAO= new RegistrationDAOimpl();
	}
	public void enrollCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException {
		registrationDAO.enrollCourse(studentId, courseId);
	}
	public void dropCourse(int registrationID) {
		registrationDAO.dropCourse(registrationID);
		
	}

}
