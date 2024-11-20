package model;

public class UndergraduateCourse extends Course implements CourseManager {
    private static final int LEVEL_ID = 1;
    private static final String REQUIREMENTS = "Must have completed high school";

    public UndergraduateCourse(String courseName, String courseDescription, int credits, int schedule, int maxCap) {
        super(courseName, courseDescription, credits, LEVEL_ID, schedule, maxCap);
        setLevelId(LEVEL_ID);
    }

    @Override
    public String getRequirements() {
        return REQUIREMENTS;
    }

    @Override
    public void registerCourse() {
        System.out.println("Registering for Undergraduate Course: " + getCourseName());
    }

    @Override
    public void dropCourse() {
        System.out.println("Dropping Undergraduate Course: " + getCourseName());
    }
}
