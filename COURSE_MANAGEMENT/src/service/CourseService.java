package service;

import dao.CourseDAO;
import dao.CourseDAOimpl;
import exception.CourseNotFoundException;
import model.DoctoralCourse;
import model.PostgraduateCourse;
import model.UndergraduateCourse;

public class CourseService {
	
	private CourseDAO courseDAO;
	
	public CourseService() {
	 courseDAO=new CourseDAOimpl();
	}
	
	public void addCourse(UndergraduateCourse undergraduateCourse) {
		courseDAO.addCourse(undergraduateCourse);
	}
	
	public void addCourse(PostgraduateCourse postgraduateCourse) {
		courseDAO.addCourse(postgraduateCourse);
	}

	public void addCourse(DoctoralCourse DoctoralCourse) {
		courseDAO.addCourse(DoctoralCourse);
	}

	public void removeCourse(int courseId) throws CourseNotFoundException {
		courseDAO.removeCourse(courseId);
		
	}

	public void viewCourse() {
		courseDAO.viewCourse();
		
	}
}
