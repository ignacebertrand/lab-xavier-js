package ca.etsmtl.log430.lab2;

/**
 * Main class for assignment 2 for LOG430, Architecture logicielle.
 * 
 * <pre>
 * <b>Pseudo Code:</b>
 * 
 *   Instantiate Teacher List, Course List
 *   do until done
 *     Present Menu
 *     if user choice = 1 then list teachers
 *     if user choice = 2 then list courses
 *     if user choice = 3 then
 *        list teachers
 *        ask user to select a teacher (by ID)
 *        list courses assigned to that teacher this term
 *     endif
 *     if user choice = 4 then
 *        list courses
 *        ask user to select a course (by ID and group)
 *        list teachers assigned to that course this term
 *     endif
 *     if user choice = 5 then
 *        list teachers
 *        ask user to select a teacher (by ID)
 *        list courses
 *        ask user to select a course (by ID and group)
 *        assign course to teacher (and vice versa)
 *     endif
 *     if user choice = x then you are done
 *   end do
 * </pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.2, 2012-Feb-02
 */

/*
 * Modification Log
 * **************************************************************************
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * **************************************************************************
 */

public class TeacherAssignment {

	public static void main(String argv[]) {

		if (argv.length != 2) {
			System.out.println("\n\nIncorrect number of input parameters -"
					+ " correct usage:");
			System.out.println("\njava TeacherAssignment <course file name>"
					+ " <teacher file name>");
		} else {

			// Declarations:

			boolean done; // Loop invariant
			char userChoice; // User's menu choice
			Course myCourse = null; // A course object
			Teacher myTeacher = null; // A teacher object

			// Instantiates a menu object
			Menus menu = new Menus();

			// Instantiates a display object
			Displays display = new Displays();

			/*
			 * The following instantiations create a list of courses and
			 * teachers. The pathname for the file containing course information
			 * is passed to the main program on the command line as the first
			 * argument (argv[0]). The pathname for the file containing teacher
			 * information is passed to the main program on the command line as
			 * the second argument (argv[1]). An example teacher file and course
			 * file is provided as enseignantsLOG.txt and coursLOG.txt
			 */

			CourseReader myCourseList = new CourseReader(argv[0]);
			TeacherReader myTeacherList = new TeacherReader(argv[1]);

			if ((myCourseList.getListOfCourses() == null)
					|| (myTeacherList.getListOfTeachers() == null)) {
				System.out
						.println("\n\n *** The course list and/or the teacher"
								+ " list was not initialized ***");
				done = true;
			} else {
				done = false;
			} // if

			while (!done) {

				userChoice = menu.mainMenu();
				switch (userChoice) {

				case '1':

					display.displayTeacherList(myTeacherList
							.getListOfTeachers());
					break;

				case '2':

					display.displayCourseList(myCourseList.getListOfCourses());
					break;

				case '3':

					display.displayTeacherList(myTeacherList
							.getListOfTeachers());
					myTeacher = menu.pickTeacher(myTeacherList
							.getListOfTeachers());
					if (myTeacher != null) {
						display.displayCoursesAssignedToTeacher(myTeacher);
					} // if
					break;

				case '4':

					display.displayCourseList(myCourseList.getListOfCourses());
					myCourse = menu.pickCourse(myCourseList.getListOfCourses());

					if (myCourse != null) {
						display.displayTeachersAssignedToCourse(myCourse);
					} // if
					break;

				case '5':

					display.displayTeacherList(myTeacherList
							.getListOfTeachers());
					myTeacher = menu.pickTeacher(myTeacherList
							.getListOfTeachers());

					if (myTeacher != null) {
						display.displayCourseList(myCourseList
								.getListOfCourses());
						myCourse = menu.pickCourse(myCourseList
								.getListOfCourses());
						if (myCourse != null) {
							myCourse.assignTeacher(myTeacher);
							myTeacher.assignCourse(myCourse);
						} // if
					} // if

					break;

				case 'X':

				case 'x':
					done = true;
				} // switch
			} // while
		} // if
	} // main
} // Class