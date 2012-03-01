package ca.etsmtl.log430.lab3;

import java.util.Observable;

/**
 * Upon notification, first lists the teachers and asks the user to pick a
 * teacher and enter the teacher's ID. If the teacher's ID is valid, then the
 * courses to that teacher are listed.
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

public class ListCoursesAssignedToTeacher extends Communication {

	public ListCoursesAssignedToTeacher(Integer registrationNumber,
			String componentName) {
		super(registrationNumber, componentName);
	}

	/**
	 * The update() method is an abstract method that is called whenever the
	 * notifyObservers() method is called by the Observable class. First we
	 * check to see if the NotificationNumber is equal to this thread's
	 * RegistrationNumber. If it is, then we execute.
	 * 
	 * @see ca.etsmtl.log430.lab3.Communication#update(java.util.Observable,
	 *      java.lang.Object)
	 */
	public void update(Observable thing, Object notificationNumber) {
		Menus menu = new Menus();
		Displays display = new Displays();
		Teacher myTeacher = new Teacher();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			/*
			 * First we use a Displays object to list all of the teachers. Then
			 * we ask the user to pick a teacher using a Menus object.
			 */
			addToReceiverList("ListTeachersComponent");
			signalReceivers("ListTeachersComponent");
			myTeacher = menu.pickTeacher(CommonData.theListOfTeachers
					.getListOfTeachers());

			/*
			 * If the user selected an invalid teacher, then a message is
			 * printed to the terminal.
			 */
			if (myTeacher != null) {
				display.displayCoursesAssignedToTeacher(myTeacher);
			} else {
				System.out.println("\n\n *** Teacher not found ***");
			}
		}
		removeFromReceiverList("ListTeachersComponent");
	}
}