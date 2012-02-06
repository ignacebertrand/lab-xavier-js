package ca.etsmtl.log430.lab2;
/**
 * This class will read from the InputFile and instantiate the Course objects
 * in the system. It is assumed that the InputFile is in the local directory,
 * contains the scheduled courses and each line of input is read and expected
 * in the following format.
 * <pre>
 *		LOG430 1 CRS ME 1030 1200 Architecture logicielle
 *		|      | |   |  |    |    |
 *		|      | |   |  |    |    Course Title
 *		|      | |   |  |    Stop Time
 *		|      | |   |  Start Time
 *		|      | |   Day
 *      |      | Type (CRS=course, LAB=laboratory)
 *		|      Group
 *		Course Number </pre>
 *
 * The coursLOG.txt file has been provided as an example file.
 *
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Feb-02
 */

/* Modification Log
 *****************************************************************************
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 *  
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS. 
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 *****************************************************************************/

public class CourseReader extends LineOfTextFileReader {

	/**
	 * List of courses.
	 */
    private CourseList listOfCourses = new CourseList();

    public CourseReader() {

        setListOfCourses(null);

    } // Constructor #1

    public CourseReader(String InputFile) {

        setListOfCourses(getCourseList(InputFile));

    } // Constructor #2

	/**
	 * Reads a line of text from the course file. The line of text is then
	 * passed to the ParseCourseText method where it is parsed and a Course
	 * object is returned. The Course object is then added to the list. When a
	 * null is read from the Course file the method terminates and returns the
	 * list to the caller. A list that points to null is an empty list.
	 * 
	 * @param inputFile
	 * @return The CourseList properly populated
	 */
    public CourseList getCourseList(String inputFile) {

        String courseText;
        boolean done;
        CourseList list = new CourseList();

        if (openFile(inputFile)) {

            done = false;

            while (!done) {

                try {

                    courseText = readLineOfText();

                    if (courseText == null) {

                        done = true;

                    } else {

                        list.addCourse(parseCourseText(courseText));

                    } // if 

                } // try

                catch (Exception Error) {

                    return (null);

                } // catch

            } // while		

        } else {

            return (null);

        } // if

        closeFile();

        return (list);

    } // GetCourseList

	/**
	 * Parse lines of text that are read from the text file containing course
	 * information in the above format. Note that this is a private method and
	 * it is used exclusively by getCourseList.
	 * 
	 * @param lineOfText
	 * @return A properly populated Course instance.
	 */
    private Course parseCourseText(String lineOfText) {

        boolean done; // Loop terminator
        String token; // String token parsed from LineOfText
        int tokenCount; // Number of tokens parsed
        int frontIndex; // Front index of token to parse
        int backIndex; // Back index of token to parse

        // Instantiate a course object

        Course newCourse = new Course();

        tokenCount = 0;
        frontIndex = 0;
        backIndex = 0;
        done = false;

        while (!done) {

            backIndex = lineOfText.indexOf(' ', frontIndex);

            if (tokenCount < 6) {

                token = lineOfText.substring(frontIndex, backIndex);

            } else {

                token = lineOfText.substring(frontIndex);

            } // if 

            switch (tokenCount) {

                case 0 : // Course number 
                    newCourse.setCourseID(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 1 : // Course group
                    newCourse.setGroup(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 2 : // Course Type
                    newCourse.setType(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 3 : // Day of the week the course will be held on
                    newCourse.setDay(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 4 : // Course start time
                    newCourse.setStartTime(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 5 : // Course stop time
                    newCourse.setStopTime(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 6 : // Course title or name of the course
                    newCourse.setCourseName(token);
                    done = true;
                    break;

            } // end switch

        } // end while

        return (newCourse);

    } // ParseCourseText

	public void setListOfCourses(CourseList listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	public CourseList getListOfCourses() {
		return listOfCourses;
	}

} // CourseReader