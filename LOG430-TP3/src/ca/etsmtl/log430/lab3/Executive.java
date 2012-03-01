package ca.etsmtl.log430.lab3;

/**
 * Acts as the system executive. It provides the primary user interface.
 * 
 * Pseudo Code:
 * 
 * <pre>
 * 	add components that I want to receive my signals to the receiver list.
 * 	while !done
 * 		Present Menu
 * 	    if user choice = 1, signal "ListTeachersComponent"
 *    	if user choice = 2, signal "ListCoursesComponent" 
 * 	    if user choice = 3, signal "ListCoursesAssignedToTeacherComponent"
 * 	    if user choice = 4, signal "ListTeachersAssignedToCourseComponent"
 * 	    if user choice = 5, signal "AssignTeacherToCourse" 
 * 	    if user choice = x, you are done
 * 	end while
 * </pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.2, 2011-Feb-24
 */

/*
 * Modification Log **********************************************************
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class Executive extends Communication {

	public Executive(Integer registrationNumber, String componentName) {
		super(registrationNumber, componentName);
	}

	/**
	 * The main execution loop.
	 */
	public void execute() {
		char userChoice;
		boolean done = false;
		Menus menu = new Menus();

		addToReceiverList("ListTeachersComponent");
		addToReceiverList("ListCoursesComponent");
		addToReceiverList("ListCoursesAssignedToTeacherComponent");
		addToReceiverList("ListTeachersAssignedToCourseComponent");
		addToReceiverList("AssignTeacherToCourse");

		while (!done) {
			userChoice = menu.mainMenu();

			switch (userChoice) {
			case '1':
				signalReceivers("ListTeachersComponent");
				break;

			case '2':
				signalReceivers("ListCoursesComponent");
				break;

			case '3':
				signalReceivers("ListCoursesAssignedToTeacherComponent");
				break;

			case '4':
				signalReceivers("ListTeachersAssignedToCourseComponent");
				break;

			case '5':
				signalReceivers("AssignTeacherToCourse");
				break;

			case 'X':
			case 'x':
				done = true;
				break;

			}
		}
	}
}