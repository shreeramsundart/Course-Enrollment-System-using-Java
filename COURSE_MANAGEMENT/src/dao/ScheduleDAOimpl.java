package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import exception.CourseNotFoundException;
import exception.ScheduleNotFoundException;
import utility.DBconnection;

public class ScheduleDAOimpl implements ScheduleDAO {

	@Override
	public void addCourseSchedule(int courseId, String dayOfWeek, String startTimeStr, String endTimeStr) {
	    if (!courseExists(courseId)) {
	        try {
	            throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
	        } catch (CourseNotFoundException e) {
	            e.printStackTrace();
	            return;
	        }
	    }

	    Time startTime = convertStringToTime(startTimeStr);
	    Time endTime = convertStringToTime(endTimeStr);

	    if (startTime == null || endTime == null) {
	        System.out.println("Invalid time format. Please use HH:mm format.");
	        return;
	    }

	    String sql = "INSERT INTO COURSESCHEDULES (course_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";

	    try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        ps.setInt(1, courseId);
	        ps.setString(2, dayOfWeek);
	        ps.setTime(3, startTime);
	        ps.setTime(4, endTime);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            // Retrieve the generated schedule_id
	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                int scheduleId = rs.getInt(1);
	                System.out.println("Course schedule added successfully with schedule ID: " + scheduleId);
	            }
	        } else {
	            System.out.println("Failed to add course schedule.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private boolean courseExists(int courseId) {
	    String sql = "SELECT COUNT(*) FROM COURSES WHERE course_id = ?";
	    try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, courseId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	private Time convertStringToTime(String timeStr) {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        java.util.Date date = sdf.parse(timeStr);
	        return new Time(date.getTime());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public void removeCourseSchedule(int scheduleId) {
	    if (!scheduleExists(scheduleId)) {
	        try {
	            throw new ScheduleNotFoundException("Course schedule with ID " + scheduleId + " not found.");
	        } catch (ScheduleNotFoundException e) {
	            e.printStackTrace();
	            return;
	        }
	    }

	    String sql = "DELETE FROM COURSESCHEDULES WHERE schedule_id = ?";

	    try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, scheduleId);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Course schedule with ID " + scheduleId + " removed successfully.");
	        } else {
	            System.out.println("Failed to remove course schedule.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private boolean scheduleExists(int scheduleId) {
	    String sql = "SELECT COUNT(*) FROM COURSESCHEDULES WHERE schedule_id = ?";
	    try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, scheduleId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

    
    

}
