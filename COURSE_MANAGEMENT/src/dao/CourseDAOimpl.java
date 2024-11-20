package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CourseNotFoundException;
import model.DoctoralCourse;
import model.PostgraduateCourse;
import model.UndergraduateCourse;
import utility.DBconnection;
import utility.CourseActivityUtil;

public class CourseDAOimpl implements CourseDAO {

	private CourseActivityUtil courseActivity= new CourseActivityUtil(); 
    @Override
    public void addCourse(UndergraduateCourse undergraduateCourse) {
        String sql = "INSERT INTO COURSES (course_name, course_description, credits, level_id, schedule, max_capacity, requirements) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBconnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, undergraduateCourse.getCourseName());
            ps.setString(2, undergraduateCourse.getCourseDescription());
            ps.setInt(3, undergraduateCourse.getCredits());
            ps.setInt(4, undergraduateCourse.getLevelId());  
            ps.setInt(5, undergraduateCourse.getSchedule());
            ps.setInt(6, undergraduateCourse.getMaxCapacity());
            ps.setString(7, undergraduateCourse.getRequirements()); 
            
            ps.executeUpdate();
            
            
            // Get generated course_id
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int courseId = generatedKeys.getInt(1);
                    System.out.println("Undergraduate course added successfully. Course ID: " + courseId);
                    courseActivity.logCourseAction("CREATED",courseId,undergraduateCourse.getCourseName());
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding course: " + undergraduateCourse.getCourseName());
        }
    }

    @Override
    public void addCourse(PostgraduateCourse postgraduateCourse) {
        String sql = "INSERT INTO COURSES (course_name, course_description, credits, level_id, schedule, max_capacity, requirements) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBconnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, postgraduateCourse.getCourseName());
            ps.setString(2, postgraduateCourse.getCourseDescription());
            ps.setInt(3, postgraduateCourse.getCredits());
            ps.setInt(4, postgraduateCourse.getLevelId());  
            ps.setInt(5, postgraduateCourse.getSchedule());
            ps.setInt(6, postgraduateCourse.getMaxCapacity());
            ps.setString(7, postgraduateCourse.getRequirements());
            
            ps.executeUpdate();
            
            // Get generated course_id
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int courseId = generatedKeys.getInt(1);
                    System.out.println("Postgraduate course added successfully. Course ID: " + courseId);
                    courseActivity.logCourseAction("CREATED",courseId,postgraduateCourse.getCourseName());
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding Postgraduate course: " + postgraduateCourse.getCourseName());
        }
    }

    @Override
    public void addCourse(DoctoralCourse doctoralCourse) {
        String sql = "INSERT INTO COURSES (course_name, course_description, credits, level_id, schedule, max_capacity, requirements) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBconnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, doctoralCourse.getCourseName());
            ps.setString(2, doctoralCourse.getCourseDescription());
            ps.setInt(3, doctoralCourse.getCredits());
            ps.setInt(4, doctoralCourse.getLevelId());  
            ps.setInt(5, doctoralCourse.getSchedule());
            ps.setInt(6, doctoralCourse.getMaxCapacity());
            ps.setString(7, doctoralCourse.getRequirements());
            
            ps.executeUpdate();
            
            // Get generated course_id
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int courseId = generatedKeys.getInt(1);
                    System.out.println("Doctoral course added successfully. Course ID: " + courseId);
                    courseActivity.logCourseAction("CREATED",courseId,doctoralCourse.getCourseName());
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding Doctoral course: " + doctoralCourse.getCourseName());
        }
    }

    @Override
    public void removeCourse(int courseId) throws CourseNotFoundException {
        String getCourseNameSql = "SELECT course_name FROM COURSES WHERE course_id = ?";
        String removeCourseSql = "{CALL removeCourse(?)}";
        String courseName = null;
        
        try (Connection con = DBconnection.getConnection()) {
            
            try (PreparedStatement ps = con.prepareStatement(getCourseNameSql)) {
                ps.setInt(1, courseId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        courseName = rs.getString("course_name");
                    } else {
                        throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
                    }
                }
            }
            try (PreparedStatement ps = con.prepareStatement(removeCourseSql)) {
                ps.setInt(1, courseId);
                ps.executeUpdate();
                System.out.println("Course removed successfully. Course ID: " + courseId);
                courseActivity.logCourseAction("REMOVED", courseId, courseName);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error removing course with ID: " + courseId);
            if (e.getMessage().contains("Course not found")) {
                throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
            }
        }
    }


	public void viewCourse() {
	    String sql = "SELECT c.course_id, c.course_name, c.course_description, c.credits, " +
	                 "c.schedule, c.max_capacity, c.requirements, cl.level_name " +
	                 "FROM COURSES c " +
	                 "INNER JOIN COURSELEVELS cl ON c.level_id = cl.level_id";
	    
	    try (Connection con = DBconnection.getConnection(); 
	         PreparedStatement ps = con.prepareStatement(sql); 
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) {
	            int courseId = rs.getInt("course_id");
	            String courseName = rs.getString("course_name");
	            String courseDescription = rs.getString("course_description");
	            int credits = rs.getInt("credits");
	            String schedule = rs.getString("schedule");
	            int maxCapacity = rs.getInt("max_capacity");
	            String requirements = rs.getString("requirements");
	            String levelName = rs.getString("level_name");
	            
	            // Display the course information
	            System.out.println("Course ID: " + courseId);
	            System.out.println("Course Name: " + courseName);
	            System.out.println("Description: " + courseDescription);
	            System.out.println("Credits: " + credits);
	            System.out.println("Schedule: " + schedule);
	            System.out.println("Max Capacity: " + maxCapacity);
	            System.out.println("Requirements: " + requirements);
	            System.out.println("Level: " + levelName);
	            System.out.println("-----------------------------");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error fetching courses.");
	    }
	}



}
