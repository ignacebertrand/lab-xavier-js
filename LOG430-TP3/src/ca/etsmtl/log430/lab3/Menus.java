package ca.etsmtl.log430.lab3;


/**
 * Presents the user with menus, accepts their choice, ensures their choice is
 * valid, and returns their choice to the caller.
 * 
 * The menu offers the following choices:
 * 
 * <pre>
 *    1) List Teachers
 *    2) List Courses
 *    3) List Courses currently assigned to a teacher this term
 *    4) List Teachers currently assigned to a course this term 
 *    5) Assign a Teacher to a Course
 *    X) Exit.
 * </pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-14
 */

/*
 * Modification Log **********************************************************
 * v1.3, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class Menus {

	/**
	 * Main menu.
	 * 
	 * @return the user's choice in the menu.
	 */
	public char mainMenu() {
		Termio terminal = new Termio();
		char userChoice = ' ';
		boolean error = true;

		while (error) {
			System.out.println("\n\n1) List Teachers");
			System.out.println("2) List Courses");
			System.out
					.println("3) List courses currently assigned to a teacher this term");
			System.out
					.println("4) List teacher(s) currently assigned to a course this term");
			System.out.println("5) Assign a teacher to a course");
			System.out.println("6) List courses previously assigned to a teacher before this term"); // LOG430 MODIFICATION 1
			System.out.println("7) List courses that still have not been assigned to a teacher this term"); // LOG430 MODIFICATION 2
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
			}
		}
		return (userChoice);
	}

	/**
	 * Allows the user to select a teacher.
	 * 
	 * @param listObject
	 * @return the Teacher the user has chosen.
	 */
	public Teacher pickTeacher(TeacherList listObject) {
		Termio terminal = new Termio();
		String userChoice;
		Teacher teacherObject = null;

		System.out.print("\n\nEnter Teacher ID and press return >> ");
		userChoice = terminal.keyboardReadString();

		teacherObject = listObject.findTeacherByID(userChoice);

		if (teacherObject == null) {
			System.out.println("\n\n*** Teacher ID " + userChoice
					+ " not found ***");
		}
		return (teacherObject);
	}

	/**
	 * Allows the user to select a teacher
	 * 
	 * @param listObject
	 * @return The course instance picked by the user
	 */
	public Course pickCourse(CourseList listObject) {
		Termio terminal = new Termio();
		String userChoiceCourseID;
		String userChoiceGroup;
		String userChoiceActivityType;
		Course courseObject = null;

		System.out.print("\nEnter Course ID and press return >> ");
		userChoiceCourseID = terminal.keyboardReadString();

		System.out.print("\nEnter group and press return >> ");
		userChoiceGroup = terminal.keyboardReadString();

		System.out
				.print("\nEnter type of activity (CRS or LAB) and press return >> ");
		userChoiceActivityType = terminal.keyboardReadString();

		courseObject = listObject.findCourseByIDActivityAndGroup(
				userChoiceCourseID, userChoiceGroup, userChoiceActivityType);

		if (courseObject == null) {
			System.out.print("\n\n*** Course ID:" + userChoiceCourseID);
			System.out
					.println(" Section:" + userChoiceGroup + " not found ***");
		}
		return (courseObject);
	}
	
	/**
	 * Demands a confirmation from the user
	 * (LOG430 MODIFICATION 3)
	 * 
	 * @return String 'Y' for Yes, 'N' for No, null for invalid answer
	 */
	public String askConfirmation() {
		Termio terminal = new Termio();
		String userChoice;
		
		System.out.print("\nConfirm your choice by entering 'Y' or cancel by entering 'N' and press return >> ");
		userChoice = terminal.keyboardReadString();
		
		if(!userChoice.equalsIgnoreCase("Y") && !userChoice.equalsIgnoreCase("N")) {
			System.out.print("\n\n*** Invalid Choice:: " + userChoice
					+ " , Cancelling***");
			userChoice = null;
		}
		
		return userChoice;
	}
}