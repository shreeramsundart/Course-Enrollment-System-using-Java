package model;

public abstract class Course {
    private String courseName;
    private String courseDescription;
    private int credits;
    private int levelId;
    private int schedule;
    private int maxCapacity;

    public Course(String courseName, String courseDescription, int credits,int levelId, int schedule, int maxCapacity) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.credits = credits;
        this.schedule = schedule;
        this.maxCapacity = maxCapacity;
        this.levelId =levelId;
    }

	public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Abstract method to get course-specific requirements
    public abstract String getRequirements();
}
