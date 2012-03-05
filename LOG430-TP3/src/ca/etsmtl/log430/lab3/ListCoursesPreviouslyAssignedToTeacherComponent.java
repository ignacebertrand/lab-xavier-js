package ca.etsmtl.log430.lab3;

import java.util.Observable;

public class ListCoursesPreviouslyAssignedToTeacherComponent extends
		Communication {

	public ListCoursesPreviouslyAssignedToTeacherComponent(
			Integer registrationNumber, String componentName) {
		super(registrationNumber, componentName);
		// TODO Auto-generated constructor stub
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
				display.displayCoursesPreviouslyAssignedToTeacher(myTeacher);
			} else {
				System.out.println("\n\n *** Teacher not found ***");
			}
		}
		removeFromReceiverList("ListTeachersComponent");
	}

}
