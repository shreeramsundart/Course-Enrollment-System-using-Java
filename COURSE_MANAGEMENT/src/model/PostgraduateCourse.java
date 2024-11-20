package model;

public class PostgraduateCourse extends Course implements CourseManager {
    private static final int LEVEL_ID = 2;
    private static final String REQUIREMENTS = "Must have completed a Bachelor's degree";

    public PostgraduateCourse(String courseName, String courseDescription, int credits, int schedule, int max_capacity) {
        super(courseName, courseDescription, credits, LEVEL_ID, schedule, max_capacity);  // Assuming maxCapacity is set as 0 or needs to be set.
        setLevelId(LEVEL_ID);
    }

    @Override
    public String getRequirements() {
        return REQUIREMENTS;
    }

    @Override
    public void registerCourse() {
        System.out.println("Registering for Postgraduate Course: " + getCourseName());
    }

    @Override
    public void dropCourse() {
        System.out.println("Dropping Postgraduate Course: " + getCourseName());
    }
}
