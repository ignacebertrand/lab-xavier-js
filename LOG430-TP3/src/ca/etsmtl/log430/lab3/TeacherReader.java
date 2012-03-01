package ca.etsmtl.log430.lab3;

/**
 * Reads from the InputFile and instantiates the Teacher objects in the system.
 * It is assumed that the InputFile is in the local directory, contains the
 * active teachers, and each line of input is read and expected in the following
 * format: a field oriented and space-separated text file that lists all the
 * teachers. The fields are as follows:
 * 
 * <pre>
 *     ETS001 Champagne Roger PRF LOG120 MAT115 CHM101
 *     |      |         |     |   |
 *     |      |         |     |   Past courses taught in the same year (before this term)
 *     |      |         |     Type of teacher (PRF=prof, CHR = Charge de cours, e.g. T.A.)
 *     |      |         Teacher's First Name
 *     |      Teacher's Last Name 
 *     Teacher ID
 * </pre>
 * 
 * The enseignantsLOG.txt file has been provided as an example.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2012-Feb-14
 */

/*
 * Modification Log
 * *********************************************************************** v1.4,
 * R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.3, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.2, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.1, 2001-Jan-25, G.A. Lewis - Bug in ParseStudentText. There was a bug in
 * that was causing it not to read the last course into the list of courses
 * taken.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ***********************************************************************
 */

public class TeacherReader extends LineOfTextFileReader {

	/**
	 * The list of teachers.
	 */
	private TeacherList listOfTeachers = new TeacherList();

	public TeacherReader() {

		listOfTeachers = null;

	} // Constructor #1

	public TeacherReader(String InputFile) {

		listOfTeachers = readTeacherListFromFile(InputFile);

	} // Constructor #2

	/**
	 * Reads a line of text. The line of text is passed to the parseTeacherText
	 * method where it is parsed and a Teacher object is returned. The Teacher
	 * object is then added to the list. When a null is read from the Teacher
	 * file the method terminates and returns the list to the caller. A list
	 * that points to null is an empty list.
	 * 
	 * @param inputFile
	 * @return The list of teachers
	 */
	public TeacherList readTeacherListFromFile(String inputFile) {

		String teacherText; // Line of text from the file
		boolean done; // End of the file - stop processing

		// New teacher list object - this will contain all of the teachers in
		// the file

		TeacherList listObject = new TeacherList();

		if (openFile(inputFile)) {

			done = false;

			while (!done) {

				try {

					teacherText = readLineOfText();

					if (teacherText == null) {

						done = true;

					} else {

						listObject.addTeacher(parseTeacherText(teacherText));

					} // if

				} // try

				catch (Exception Error) {

					return (null);

				} // catch

			} // while

		} else {

			return (null);

		} // if

		return (listObject);

	} // readTeacherListFromFile

	public TeacherList getListOfTeachers() {
		return listOfTeachers;
	}

	public void setListOfTeachers(TeacherList listOfTeachers) {
		this.listOfTeachers = listOfTeachers;
	}

	/**
	 * Parse lines of text that are read from the text file containing teacher
	 * information in the above format.
	 * 
	 * @param lineOfText
	 * @return populated Teacher object
	 */
	private Teacher parseTeacherText(String lineOfText) {

		boolean done = false; // Indicates the end of parsing
		String token; // String token parsed from the line of text
		int tokenCount = 0; // Number of tokens parsed
		int frontIndex = 0; // Front index or character position
		int backIndex = 0; // Rear index or character position

		// Create a teacher object to record all of the info parsed from
		// the line of text

		Teacher teacher = new Teacher();

		while (!done) {

			backIndex = lineOfText.indexOf(' ', frontIndex);

			if (backIndex == -1) {

				done = true;
				token = lineOfText.substring(frontIndex);

			} else {

				token = lineOfText.substring(frontIndex, backIndex);
			}

			switch (tokenCount) {

			case 0: // Teacher ID
				teacher.setTeacherID(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 1: // Teacher's last name
				teacher.setLastName(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 2: // Teacher's First name
				teacher.setFirstName(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 3: // Teacher type (e.g. PRF, CHR)
				teacher.setType(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			default:
				// This is where the courses are added to the teachers'
				// courses taught list within the teacher object
				// Note that there are no details other than the course
				// ID that is recorded in the teacher's courses taught
				// list.
				teacher.getCoursesTaughtList().addCourse(new Course(token));
				frontIndex = backIndex + 1;
				break;

			} // end switch

		} // end while

		return (teacher);

	} // parseTeacherText

} // TeacherReader