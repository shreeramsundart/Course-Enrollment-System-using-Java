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

public class EnrollmentActivityUtil {
    public static final String FILE_PATH = "enrollment_activity.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void logEnrollmentAction(String actionType, int studentId, String firstName, String lastName, int courseId) {
        String timestamp = dateFormat.format(new Date());
        String record = String.format("%s | Action: %s | Student ID: %d | First Name: %s | Last Name: %s | Course ID: %d",
                                       timestamp, actionType, studentId, firstName, lastName, courseId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to enrollment activity file...");
        }
    }

    public List<String> retrieveEnrollmentActivity() {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading enrollment activity file...");
        }
        return history;
    }
}
