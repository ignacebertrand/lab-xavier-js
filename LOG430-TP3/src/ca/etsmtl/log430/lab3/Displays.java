package ca.etsmtl.log430.lab3;


/**
 * Displays various types of information on courses and teachers (individually
 * and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-14
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.3, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
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
	 * the teachers registered for the course are not listed by this method.
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

	} // displayTeachersAssignedToCourse

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
	 * Displays the teachers in the teacher list. Displays the same information
	 * that is listed in the displayTeacher() method listed above.
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

	} // DisplayTeacherList

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

	/**
	 * Lists the courses previously assigned to a teacher before this term.
	 * (LOG430 MODIFICATION 1)
	 * 
	 * @param teacher
	 */
	public void displayCoursesPreviouslyAssignedToTeacher(Teacher teacher) {
		boolean done;
		Course course;

		System.out.print("\nCourses assigned before this term to : "
				+ teacher.getFirstName() + " ");
		System.out
				.println(teacher.getLastName() + " " + teacher.getTeacherID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		teacher.getCoursesTaughtList().goToFrontOfList();
		done = false;

		while (!done) {
			course = teacher.getCoursesTaughtList().getNextCourse();

			if (course == null) {
				done = true;
			} else {
				System.out.println(course.getCourseID());
				lineCheck(1);
			} // if
		} // while
	} // displayCoursesPreviouslyAssignedToTeacher

	/**
	 * Lists the courses that are still not currently assigned to a teacher this term.
	 * (LOG430 MODIFICATION 2)
	 * 
	 * @param cList
	 */
	public void displayUnassignedCourses(CourseList cList) {
		boolean done;
		Course course;
		TeacherList tList;

		System.out.println("Courses still unassigned this term : ");
		lineCheck(1);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		done = false;
		cList.goToFrontOfList();

		while (!done) {
			course = cList.getNextCourse();

			if (course == null) {
				done = true;
			} else {
				tList = course.getTeachers();
				
				// Affiche les cours avec aucun enseignant
				// qui ne sont pas de type LAB
				if(tList.size() == 0 && !course.getType().equalsIgnoreCase("LAB")) {
					displayCourse(course);
					lineCheck(2);
				} // if
			} // if
		} // while
	} // displayUnassignedCourses
	
	/**
	 * Displays a warning when a course is about to be assigned 
	 * to more than one teacher for the same term.
	 * (LOG430 MODIFICATION 3)
	 * 
	 * @param teacher
	 */
	public void displayMultipleAssignmentWarning(Teacher teacher) {
		
		System.out.println("\nWarning! This course is already assigned for this term.");
		lineCheck(2);
		System.out.print("\nDo you also want to assign this course to : "
				+ teacher.getFirstName() + " ");
		System.out
				.println(teacher.getLastName() + " " + teacher.getTeacherID() + "?");
		lineCheck(2);
		
	} // displayMultipleAssignmentWarning
	
	/**
	 * Displays a warning when a teacher has a schedule conflict
	 * with a course that is about to be assigned to him
	 * (LOG430 MODIFICATION 4a)
	 * 
	 * @param teacher
	 */
	public void displayScheduleConflictWarning(Teacher teacher) {
		
		System.out.println("\nWarning! Assigning this course to " + teacher.getFirstName()
				+ " " + teacher.getLastName() + " would generate a schedule conflict.");
		lineCheck(2);
		System.out.println("\nSystem can not comply with requested operation. Sorry!");
		lineCheck(2);
		
	} // displayScheduleConflictWarning
	
	/**
	 * Displays a warning when a course is about to be assigned 
	 * to a teacher whose maximum number of assignments for the
	 * year has already been reached.
	 * (LOG430 MODIFICATION 4b)
	 * 
	 * @param teacher
	 */
	public void displayMaximumNumberOfAssignmentsWarning(Teacher teacher) {
		
		System.out.println("\nWarning! " + teacher.getFirstName()+ " " + teacher.getLastName()
				+ " has already reached his maximum number of CRS assignments allowed for the year.");
		lineCheck(2);
		System.out.println("\nSystem can not comply with requested operation. Sorry!");
		lineCheck(2);
		
	} // displayMaximumNumberOfAssignmentsWarning

} // Display