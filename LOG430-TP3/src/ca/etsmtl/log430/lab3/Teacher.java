package ca.etsmtl.log430.lab3;

/**
 * Defines the Teacher object for the system. Besides the static attributes,
 * there are two lists maintained. coursesTaughtList is a CourseList object that
 * maintains a list of courses that the teacher has taught prior to this term.
 * coursesAssignedList is also a CourseList object that maintains a list of
 * courses that are currently assigned to the teacher this term.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-14
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.3, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * 
 * **************************************************************************
 */

public class Teacher {

	/**
	 * Teacher's Last Name
	 */
	private String lastName;

	/**
	 * Teacher's First Name
	 */
	private String firstName;

	/**
	 * Teacher's identification number
	 */
	private String teacherID;

	/**
	 * teacher type (PRF = prof, CHR = charge de cours, e.g. T.A. in French)
	 */
	private String teacherType;

	/**
	 * List of courses previously taught by the teacher
	 */
	private CourseList coursesTaughtList = new CourseList();

	/**
	 * List of courses currently assigned to this teacher this term.
	 */
	private CourseList coursesAssignedList = new CourseList();

	/**
	 * Assigns a course by adding it to this teacher's courses assigned list.
	 * 
	 * @param course
	 */
	public void assignCourse(Course course) {

		getCoursesAssigned().addCourse(course);

	} // Register

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setType(String teacherType) {
		this.teacherType = teacherType;
	}

	public String getType() {
		return teacherType;
	}

	public void setCoursesTaughtList(CourseList coursesTaughtList) {
		this.coursesTaughtList = coursesTaughtList;
	}

	public CourseList getCoursesTaughtList() {
		return coursesTaughtList;
	}

	public void setCoursesAssigned(CourseList coursesAssigned) {
		this.coursesAssignedList = coursesAssigned;
	}

	public CourseList getCoursesAssigned() {
		return coursesAssignedList;
	}

} // Teacher class