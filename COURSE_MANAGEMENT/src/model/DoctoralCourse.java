package model;

public class DoctoralCourse extends Course implements CourseManager {
    private static final int LEVEL_ID = 3;
    private static final String REQUIREMENTS = "Must have completed a Master's degree";

    public DoctoralCourse(String courseName, String courseDescription, int credits, int schedule, int maxCapacity) {
        super(courseName, courseDescription, credits, LEVEL_ID,schedule, maxCapacity);  // Assuming maxCapacity is set as 0 or needs to be set.
        setLevelId(LEVEL_ID);
    }

    @Override
    public String getRequirements() {
        return REQUIREMENTS;
    }

    @Override
    public void registerCourse() {
        System.out.println("Registering for Doctoral Course: " + getCourseName());
    }

    @Override
    public void dropCourse() {
        System.out.println("Dropping Doctoral Course: " + getCourseName());
    }
}
