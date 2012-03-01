package ca.etsmtl.log430.lab3;

import java.util.Observable;

/**
 * Upon notification, the user is prompted to enter a course number.
 * Teachers assigned to the course are then listed.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-14
 */


/*
 * Modification Log **********************************************************
 * 
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

public class ListTeachersAssignedToCourse extends Communication {

	public ListTeachersAssignedToCourse(Integer registrationNumber,
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
		Course myCourse = new Course();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			addToReceiverList("ListCoursesComponent");
			signalReceivers("ListCoursesComponent");

			// Next we ask them to pick a course
			myCourse = menu.pickCourse(CommonData.theListOfCourses
					.getListOfCourses());

			if (myCourse != null) {
				/*
				 * If the course is valid (exists in the list), them we display
				 * the teachers that are assigned in the course object's internal
				 * list
				 */
				display.displayTeachersAssignedToCourse(myCourse);
			} else {
				System.out.println("\n\n *** Course not found ***");
			}
		}
		removeFromReceiverList("ListCoursesComponent");
	}
}