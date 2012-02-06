package ca.etsmtl.log430.lab2;

/**
 * This class is used by various other classes that need to keep a list of
 * courses. It extends the List class which provides the basic functionality for
 * storage and retrieval of the Course Object from the list.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-02
 */

/*
 * Modification Log
 * ****************************************************************************
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ***************************************************************************
 */

public class CourseList extends List {

	public CourseList() {

		super();

	} // Constructor

	/**
	 * @param course
	 *            New course to be added to the list. All the issues of casting
	 *            are taken care of within this class.
	 */
	public void addCourse(Course course) {
		appendItemToList((Object) course);
	} // AddCourse

	/**
	 * @return The course pointed at the current position pointed to by the
	 *         internal list pointer of the internal list. Subsequent calls will
	 *         return the next Course object in the list. A null object is
	 *         returned if list is empty or the end of list has been reached.
	 */
	public Course getNextCourse() {
		return (Course) getItemFromList();
	} // GetNextCourse

	/**
	 * This method assumes that all courses have different identification
	 * numbers.
	 * 
	 * @param course
	 * @return A Course instance if found in the list based on specified
	 *         criteria, null otherwise.
	 */
	public boolean findCourse(Course course) {

		Course currentObject;
		boolean done = false;
		boolean result = false;

		goToFrontOfList();

		while (!done) {

			currentObject = getNextCourse();

			if (currentObject == null) {

				done = true;

			} else {
				if (course.getCourseID().compareToIgnoreCase(
						currentObject.getCourseID()) == 0) {

					result = true;

				} // if

			} // if

		} // while

		return (result);

	} // FindCourse

	/**
	 * This method assumes that all courses have different identification
	 * numbers.
	 * 
	 * @param courseID
	 * @param group
	 * @return A Course instance if found in the list based on specified
	 *         criteria, null otherwise.
	 */
	public Course findCourseByIDAndGroup(String courseID, String group) {

		Course currentObject = null;
		boolean done = false;
		boolean found = false;

		goToFrontOfList();

		while (!done) {

			currentObject = getNextCourse();

			if (currentObject == null) {

				done = true;

			} else {

				if ((currentObject.getCourseID().compareToIgnoreCase(courseID) == 0)
						&& (currentObject.getGroup().compareToIgnoreCase(
								group) == 0)) {

					found = true;
					done = true;

				} // if

			} // if

		} // while

		if (found) {

			return (currentObject);

		} else {

			return (null);

		} // if

	} // findCourseByIDAndGroup

} // CourseList
