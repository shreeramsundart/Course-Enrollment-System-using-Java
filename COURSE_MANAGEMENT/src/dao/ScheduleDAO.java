package dao;

public interface ScheduleDAO {

	void addCourseSchedule(int courseId, String dayOfWeek, String startTimeStr, String endTimeStr);

	void removeCourseSchedule(int scheduleId);

}
