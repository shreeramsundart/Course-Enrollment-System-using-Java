package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class CourseActivityUtil {
    public static final String FILE_PATH = "course_activity.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void logCourseAction(String actionType, int courseId, String courseName) {
        String timestamp = dateFormat.format(new Date());
        String record = String.format("%s | Action: %s | Course ID: %d | Course Name: %s",
                                       timestamp, actionType, courseId, courseName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to course activity file...");
        }
    }

    public List<String> retrieveCourseActivity() {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading course activity file...");
        }
        return history;
    }
}
