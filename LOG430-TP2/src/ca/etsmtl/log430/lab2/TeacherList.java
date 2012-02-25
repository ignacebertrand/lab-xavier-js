package ca.etsmtl.log430.lab2;

/**
 * This class is used by various other classes that need to keep a list of
 * teachers on hand. It extends the List class which provides the basic
 * functionality for storage and retrieval of the Teacher Object from the list.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-02
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
 * **************************************************************************
 */

public class TeacherList extends List {

	/**
	 * Adds a new teacher to the list. All the issues of casting are taken care
	 * of within this class.
	 * 
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher) {
		appendItemToList((Object) teacher);
	} // addTeacher

	/**
	 * @return The teacher at the current position pointed to by the
	 *         internal list pointer. Subsequent calls will return the next
	 *         teacher object in the list. A null object is returned if list is
	 *         empty or the end of list has been reached.
	 */
	public Teacher getNextTeacher() {
		return (Teacher) getItemFromList();
	} // getNextTeacher

	/**
	 * Determines whether the Teacher OBJ is currently in the student list.
	 * 
	 * @param teacher
	 * @return true if the teacher is in the list, false otherwise.
	 */
	public boolean findTeacher(Teacher teacher) {

		Teacher currentObject;
		boolean done = false;
		boolean result = false;

		// Note that this method assumes that all teachers have different
		// identification numbers.

		goToFrontOfList();

		while (!done) {

			currentObject = getNextTeacher();

			if (currentObject == null) {

				done = true;

			} else {

				if (teacher.getTeacherID().compareTo(
						currentObject.getTeacherID()) == 0) {

					result = true;

				} // if

			} // if

		} // while

		return (result);

	} // findTeacher

	/**
	 * Finds a teacher in a list using the teacherID as the search key.
	 * 
	 * @param teacherID
	 * @return if the current list object's teacherID matches the teacherID
	 *         passed to the method, the Teacher object is returned to the
	 *         caller. Otherwise, returns null.
	 */
	public Teacher findTeacherByID(String teacherID) {

		Teacher currentObject = null;
		boolean done = false;
		boolean found = false;

		// Note that this method assumes that all courses have different
		// identification numbers.

		goToFrontOfList();

		while (!done) {

			currentObject = getNextTeacher();

			if (currentObject == null) {

				done = true;

			} else {

				if (currentObject.getTeacherID().compareTo(teacherID) == 0) {

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

	} // findTeacherByID

} // TeacherList