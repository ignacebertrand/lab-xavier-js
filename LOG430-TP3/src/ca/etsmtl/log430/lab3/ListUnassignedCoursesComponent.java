package ca.etsmtl.log430.lab3;

import java.util.Observable;

public class ListUnassignedCoursesComponent extends Communication{

	public ListUnassignedCoursesComponent(Integer registrationNumber,
			String componentName) {
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
		
		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			
			Displays display = new Displays();
			display.displayUnassignedCourses(CommonData.theListOfCourses
					.getListOfCourses());
			
		}
	}
}
