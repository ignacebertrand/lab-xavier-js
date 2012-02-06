package ca.etsmtl.log430.lab2;

/**
 * This class displays various types of information on courses and teachers
 * (individually and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-02
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ************************************************************************
 */

public class Displays {

	private int lineCount = 0;
	private int maxLinesDisplayed = 18;

	/**
	 * Counts the number of lines that has been printed. One a set number of
	 * lines has been printed, the user is asked to press the enter key to
	 * continue. This prevents lines of text from scrolling off of the page
	 * 
	 * @param linesToAdd
	 */
	private void lineCheck(int linesToAdd) {

		Termio terminal = new Termio();

		if (lineCount >= maxLinesDisplayed) {

			lineCount = 0;
			System.out.print("\n*** Press Enter To Continue ***");
			terminal.keyboardReadChar();

		} else {

			lineCount += linesToAdd;

		} // if

	} // LineCheck

	/**
	 * Displays a teacher object elements as follows: Teacher's first name,
	 * Teacher's last name, Teacher's ID number, Teacher's type (e.g. PRF, CHR)
	 * 
	 * Note that the courses previously taught by to the teacher and the courses
	 * the teacher has been assigned for the current term are not listed.
	 * 
	 * @param teacher
	 */
	public void displayTeacher(Teacher teacher) {

		System.out.print(teacher.getTeacherID() + " " + teacher.getFirstName()
				+ " ");
		System.out.println(teacher.getLastName() + " " + teacher.getType());

	} // displayTeacher

	/**
	 * Displays a course object elements as follows: Course title, Course
	 * number, Course section (group), Days the course will be held on, Start
	 * time of the course, Stop time of the course, Instructor Name. Note that
	 * the students registered for the course are not listed by this method.
	 * 
	 * @param course
	 */
	public void displayCourse(Course course) {

		System.out.print(course.getCourseID() + " " + course.getGroup() + " "
				+ course.getCourseName() + " " + course.getType() + " "
				+ course.getDay() + " ");
		System.out.println(course.getStartTime() + ":" + course.getStopTime());

	} // DisplayCourse

	/**
	 * Lists the teachers that have been assigned for the course.
	 * 
	 * @param course
	 */
	public void displayTeachersAssignedToCourse(Course course) {

		boolean done;
		Teacher teacher;

		System.out.println("\nTeachers assigned to: " + " ");
		lineCheck(2);

		System.out.print(course.getCourseName() + " " + course.getCourseID()
				+ "-" + course.getGroup() + " " + course.getType() + " "
				+ course.getDay() + " ");
		System.out.println(course.getStartTime() + ":" + course.getStopTime());
		lineCheck(1);

		System.out
				.println("===========================================================");
		lineCheck(1);

		course.getTeachers().goToFrontOfList();
		done = false;

		while (!done) {

			teacher = course.getTeachers().getNextTeacher();

			if (teacher == null) {

				done = true;

			} else {

				displayTeacher(teacher);

			} // if

		} // while

	} // DisplayStudentsRegistered

	/**
	 * Lists the courses currently assigned to a teacher this term.
	 * 
	 * @param teacher
	 */
	public void displayCoursesAssignedToTeacher(Teacher teacher) {

		boolean done;
		Course course;

		System.out.print("\nCourses assigned (this term) to : "
				+ teacher.getFirstName() + " ");
		System.out
				.println(teacher.getLastName() + " " + teacher.getTeacherID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		teacher.getCoursesAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			course = teacher.getCoursesAssigned().getNextCourse();

			if (course == null) {

				done = true;

			} else {

				displayCourse(course);
				lineCheck(2);

			} // if

		} // while

	} // displayCoursesAssignedToTeacher

	/**
	 * Displays the students in the student list. Displays the same information
	 * that is listed in the displayStudent() method listed above.
	 * 
	 * @param list
	 */
	public void displayTeacherList(TeacherList list) {

		boolean done;
		Teacher teacher;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();

		done = false;

		while (!done) {

			teacher = list.getNextTeacher();

			if (teacher == null) {

				done = true;

			} else {

				displayTeacher(teacher);
				lineCheck(1);

			} // if

		} // while

	} // DisplayStudentList

	/**
	 * Displays the courses in the course list. Displays the same information
	 * that is listed in the displayCourse() method listed above.
	 * 
	 * @param list
	 */
	public void displayCourseList(CourseList list) {

		boolean done;
		Course course;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();
		done = false;

		while (!done) {

			course = list.getNextCourse();

			if (course == null) {

				done = true;

			} else {

				displayCourse(course);
				lineCheck(1);

			} // if

		} // while

	} // DisplayCourseList

} // Display