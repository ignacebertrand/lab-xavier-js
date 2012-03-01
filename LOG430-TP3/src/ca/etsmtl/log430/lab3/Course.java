package ca.etsmtl.log430.lab3;

/**
 * Defines the Course object for the system.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2012-Feb-14
 */

/*
 * Modification Log **********************************************************
 * 
 * v1.4, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2011-Feb-02 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.2, R. Champagne, 2002-May-21 - Adapted for use at ETS.
 * 
 * v1.1, G.A.Lewis, 01/25/2001 - Bug in second constructor. Removed null
 * assignment to courseID after being assigned a value.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class Course {

	/**
	 * Course title
	 */
	private String courseName;

	/**
	 * Course ID
	 */
	private String courseID;

	/**
	 * Course type (course = CRS, laboratory = LAB)
	 */
	private String type;

	/**
	 * Course group
	 */
	private String group;

	/**
	 * Day the course is held on
	 */
	private String day;

	/**
	 * Start time of the course
	 */
	private String startTime;

	/**
	 * Stop time of the course
	 */
	private String stopTime;

	/**
	 * List of teachers assigned to the course 
	 */
	private TeacherList teachersAssigned = new TeacherList();

	public Course() {
		this(null);
	} // Constructor

	public Course(String CourseID) {
		this.setCourseID(CourseID);
		this.setCourseName(null);
		this.setType(null);
		this.setGroup(null);
		this.setDay(null);
		this.setStartTime(null);
		this.setStopTime(null);
	} // Constructor


	/**
	 * Assign a teacher to a class.
	 * 
	 * @param teacher
	 */
	public void assignTeacher(Teacher teacher) {
		teachersAssigned.addTeacher(teacher);
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup() {
		return group;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDay() {
		return day;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public TeacherList getTeachers() {
		return teachersAssigned;
	}

	public void setTeachers(TeacherList teachers) {
		teachersAssigned = teachers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

} // Course class