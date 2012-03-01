package ca.etsmtl.log430.lab3;

/**
 * Contains data that is used (directly or indirectly) by all
 * classes.
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

public class CommonData {
	/** The file that contains the information for courses. */
	private String defaultCourseFile = "coursLOG.txt";

	/** The file that contains the information for teachers. */
	private String defaultTeacherFile = "enseignantsLOG.txt";
	
	/** Variable used to determine if initialization has been realized. */
	private static boolean initialized = false;

	/** Object that reads the courses from a file. */
	static CourseReader theListOfCourses;

	/** Object that reads the teachers from a file. */
	static TeacherReader theListOfTeachers;

	/** A list of components */
	private static ComponentList systemComponents;

	/**
	 * Initializes the teacher and course list using the default lists
	 */
	public CommonData() {
		if (!initialized) {
			theListOfCourses = new CourseReader(defaultCourseFile);
			theListOfTeachers = new TeacherReader(defaultTeacherFile);
			systemComponents = new ComponentList();
			initialized = true;
		} // if

		// If either list is null, you are in trouble.
		if (theListOfCourses.getListOfCourses() == null) {
			System.out.println("\n\n *** The course list is empty ***");
		}
		
		if (theListOfTeachers.getListOfTeachers() == null) {
			System.out.println("\n\n *** The teacher list is empty ***");
		}
	}

	/**
	 * Updates an internal list of system components. This lets system
	 * components know the identity of other components in the system
	 * 
	 * @param component
	 */
	public void registerComponent(Communication component) {
		systemComponents.addComponent(component);
	}

	/**
	 * @param componentName
	 * @return object that corresponds to the object's instance name
	 */
	public Communication getComponent(String componentName) {
		return (systemComponents.getComponent(componentName));
	}

	/**
	 * @param componentName
	 * @return registration ID that corresponds to the object's instance name
	 */
	public Integer getComponentID(String componentName) {
		return (systemComponents.getComponentID(componentName));
	}
}