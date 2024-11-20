package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CourseNotFoundException;
import exception.RegistrationNotFoundException;
import exception.StudentNotFoundException;
import utility.DBconnection;
import utility.EnrollmentActivityUtil;

public class RegistrationDAOimpl implements RegistrationDAO {

    private EnrollmentActivityUtil enrollmentActivityUtil = new EnrollmentActivityUtil();  // Create an instance of the utility

    @Override
    public void enrollCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException {
        String checkStudentQuery = "SELECT COUNT(*) FROM STUDENTS WHERE student_id = ?";
        String checkCourseQuery = "SELECT COUNT(*) FROM COURSES WHERE course_id = ?";
        String enrollQuery = "INSERT INTO REGISTRATIONS (student_id, course_id, status) VALUES (?, ?, 'ACTIVE')";

        try (Connection con = DBconnection.getConnection()) {
            try (PreparedStatement ps1 = con.prepareStatement(checkStudentQuery)) {
                ps1.setInt(1, studentId);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next() && rs1.getInt(1) == 0) {
                    throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
                }
            }
            
            try (PreparedStatement ps2 = con.prepareStatement(checkCourseQuery)) {
                ps2.setInt(1, courseId);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next() && rs2.getInt(1) == 0) {
                    throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
                }
            }

            try (PreparedStatement ps3 = con.prepareStatement(enrollQuery)) {
                ps3.setInt(1, studentId);
                ps3.setInt(2, courseId);
                ps3.executeUpdate();
                System.out.println("Student with ID " + studentId + " successfully enrolled in course ID " + courseId);

                String getStudentQuery = "SELECT first_name, last_name FROM STUDENTS WHERE student_id = ?";
                try (PreparedStatement ps4 = con.prepareStatement(getStudentQuery)) {
                    ps4.setInt(1, studentId);
                    ResultSet rs4 = ps4.executeQuery();
                    if (rs4.next()) {
                        String firstName = rs4.getString("first_name");
                        String lastName = rs4.getString("last_name");

                        enrollmentActivityUtil.logEnrollmentAction("ENROLLED", studentId, firstName, lastName, courseId);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error enrolling student in course");
        }
    }

    @Override
    public void dropCourse(int registrationID) {
        String checkRegistrationQuery = "SELECT COUNT(*) FROM REGISTRATIONS WHERE registration_id = ?";
        String updateStatusQuery = "UPDATE REGISTRATIONS SET status = 'DROPPED' WHERE registration_id = ?";

        try (Connection con = DBconnection.getConnection()) {
          
            try (PreparedStatement ps1 = con.prepareStatement(checkRegistrationQuery)) {
                ps1.setInt(1, registrationID);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next() && rs1.getInt(1) == 0) {
                    throw new RegistrationNotFoundException("Registration with ID " + registrationID + " not found.");
                }
            }

            try (PreparedStatement ps2 = con.prepareStatement(updateStatusQuery)) {
                ps2.setInt(1, registrationID);
                ps2.executeUpdate();
                System.out.println("Registration with ID " + registrationID + " has been dropped.");
            }
            
            String getStudentQuery = "SELECT student_id, course_id FROM REGISTRATIONS WHERE registration_id = ?";
            try (PreparedStatement ps3 = con.prepareStatement(getStudentQuery)) {
                ps3.setInt(1, registrationID);
                ResultSet rs2 = ps3.executeQuery();
                if (rs2.next()) {
                    int studentId = rs2.getInt("student_id");
                    int courseId = rs2.getInt("course_id");

                    String getStudentDetailsQuery = "SELECT first_name, last_name FROM STUDENTS WHERE student_id = ?";
                    try (PreparedStatement ps4 = con.prepareStatement(getStudentDetailsQuery)) {
                        ps4.setInt(1, studentId);
                        ResultSet rs3 = ps4.executeQuery();
                        if (rs3.next()) {
                            String firstName = rs3.getString("first_name");
                            String lastName = rs3.getString("last_name");

                            enrollmentActivityUtil.logEnrollmentAction("DROPPED", studentId, firstName, lastName, courseId);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error dropping course registration.");
        }
    }
}
