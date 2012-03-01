/**
 * Assignment 3, ETS course LOG430 - Architecture logicielle. The purpose of
 * this assignment is to introduce the implicit invocation architectural
 * style.<br><br>
 * 
* The application is a contrived academic course management system. It is a menu
 * driven system that allows the following options:
 * 
 * <pre>
 *    1) List Teachers
 *    2) List Courses
 *    3) List Courses currently assigned to a teacher this term
 *    4) List Teachers currently assigned to a course this term 
 *    5) Assign a Teacher to a Course
 *    X) Exit.
 * </pre>
 * 
 * Functionally, the system is identical to the previous lab.
 * 
 * Dynamically, the main program initializes the primary objects
 * and dispatches commands at the user's request. When the program is started,
 * the teacher objects are initialized from a file (<tt>enseignantsLOG.txt</tt>).
 * The format of this file is listed in the
 * {@link ca.etsmtl.log430.lab3.TeacherReader TeacherReader} class header.
 * The course objects are initialized from another file (<tt>coursLOG.txt</tt>).
 * The format of this file is listed in the
 * {@link ca.etsmtl.log430.lab3.CourseReader CourseReader} class header.<br><br>
 * 
 * <b>Running the program:</b><br><br>
 * 
 * <blockquote>
 * <tt>java SystemInitialize</tt>
 * </blockquote>
 *
 * @author A.J. Lattanze - CMU - 1999
 * @author Roger Champagne - ETS - 2002-2012
 * @version 2012-Feb-14
 */
package ca.etsmtl.log430.lab3;