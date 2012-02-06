package ca.etsmtl.log430.lab2;

/**
 * This class presents the user with menus, accepts their choice, ensures their
 * choice is valid, and returns their choice to the caller. The menu is
 * presented as follows:
 * <pre>
 *    1) List Teachers
 *    2) List Courses
 *    3) List Courses currently assigned to a teacher this term
 *    4) List Teachers currently assigned to a course this term 
 *    5) Assign a Teacher to a Course
 *    X) Exit.</pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-02.
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ***************************************************************************
 */

public class Menus {

	public char mainMenu() {

		Termio terminal = new Termio();
		char userChoice = ' ';
		boolean error = true;

		while (error) {

			System.out.println("\n\n1) List Teachers");
			System.out.println("2) List Courses");
			System.out.println("3) List courses currently assigned to a teacher this term");
			System.out.println("4) List teacher(s) currently assigned to a course this term");
			System.out.println("5) Assign a teacher to a course");
			System.out.println("X) Exit");
			System.out.print("\n\nEnter your choice and press return >> ");

			userChoice = terminal.keyboardReadChar();

			if ((userChoice != 'X') && (userChoice != 'x')
					&& (userChoice < '1') && (userChoice != '2')
					&& (userChoice != '3') && (userChoice < '4')
					&& (userChoice != '5')) {

				System.out.print("\n\n*** Invalid Choice:: " + userChoice
						+ " ***");

			} else {

				error = false;

			} // if

		} // while

		return (userChoice);

	} // MainMenu

	public Teacher pickTeacher(TeacherList list) {

		Termio terminal = new Termio();
		String userChoice;
		Teacher teacher = null;

		System.out.print("\n\nEnter Teacher ID and press return >> ");
		userChoice = terminal.keyboardReadString();

		teacher = list.findTeacherByID(userChoice);

		if (teacher == null) {

			System.out.println("\n\n*** Teacher ID " + userChoice
					+ " not found ***");

		} // if

		return (teacher);

	} // pickTeacher

	public Course pickCourse(CourseList list) {

		Termio terminal = new Termio();
		String userChoiceCourseID;
		String userChoiceGroup;
		Course course = null;

		System.out.print("\n\nEnter Course ID and press return >> ");
		userChoiceCourseID = terminal.keyboardReadString();

		System.out.print("\n\nEnter group and press return >> ");
		userChoiceGroup = terminal.keyboardReadString();

		course = list.findCourseByIDAndGroup(userChoiceCourseID,
				userChoiceGroup);

		if (course == null) {
			System.out.print("\n\n*** Course ID:" + userChoiceCourseID);
			System.out.println(" Group:" + userChoiceGroup
					+ " not found ***");

		} // if

		return (course);

	} // pickCourse

} // Menus
