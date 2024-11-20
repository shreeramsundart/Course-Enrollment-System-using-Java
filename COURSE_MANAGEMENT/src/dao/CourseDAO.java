package dao;

import exception.CourseNotFoundException;
import model.DoctoralCourse;
import model.PostgraduateCourse;
import model.UndergraduateCourse;

public interface CourseDAO {

	void addCourse(UndergraduateCourse undergraduateCourse);

	void addCourse(PostgraduateCourse postgraduateCourse);
	
	void addCourse(DoctoralCourse doctoralCourse);

	void removeCourse(int courseId) throws CourseNotFoundException;

	void viewCourse();

}
