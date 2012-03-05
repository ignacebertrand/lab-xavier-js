package ca.etsmtl.log430.lab3;

/**
 * This class contains various validations to be made when
 * the user attempts to assign a course to a teacher.
 * 
 * @author Martin Gingras, ETS student
 * @version 1.4, 2012-Feb-24
 */

public class Validator {

	/**
	 * Detects if a course is already assigned to
	 * at least one teacher.
	 * (LOG430 MODIFICATION 3)
	 * 
	 * @param course
	 * @return boolean
	 */
	public boolean isCourseAlreadyAssigned(Course course) {
		boolean assigned = false;
		TeacherList tList = course.getTeachers();
		// Deja un prof d'assigner
		if(tList.size() > 0 ) {
			assigned = true;
		}
		return assigned;
	}
	
	/**
	 * Detects if a course to be assigned generates a schedule 
	 * conflict for the teacher it is about to be assigned to.
	 * (LOG430 MODIFICATION 4a)
	 * 
	 * @param course
	 * @param teacher
	 * @return boolean
	 */
	public boolean doesNewAssignmentCreateScheduleConflict(Course course, Teacher teacher) {
		boolean conflict = false;
		boolean done = false;
		CourseList cList = teacher.getCoursesAssigned();
		Course courseInList;
		cList.goToFrontOfList();
		
		// On n'a qu'a convertir les temps en entiers et comparer les bornes
		// lorsque la journee est la meme
		int cStart = Integer.parseInt(course.getStartTime());
		int cStop = Integer.parseInt(course.getStopTime());
		int cilStart, cilStop;
		String cDay = course.getDay();
		String cilDay;

		while (!done) {
			courseInList = cList.getNextCourse();
			
			if(courseInList == null) {
				done = true;
			}
			// Trois verifications par cours assigner VS nouveau cours
			// lorsque le jour est le meme
			else {
				cilStart = Integer.parseInt(courseInList.getStartTime());
				cilStop = Integer.parseInt(courseInList.getStopTime());
				cilDay = courseInList.getDay();
				
				// Verifier d'abord si la journee du nouveau cours et
				// celle du cours de la liste est la meme
				if(cDay.equalsIgnoreCase(cilDay)) {
					// Debut du nouveau cours est entre le debut (inclusif)
					// et la fin d'un des cours deja assigner
					if(cStart >= cilStart && cStart < cilStop) {
						conflict = true;
						//System.out.println("CONFLIT CAS 1");
						break;
					}
					// Fin du nouveau cours est entre le debut et la fin
					// d'un des cours deja assigner
					else if(cStop > cilStart && cStop < cilStop) {
						conflict = true;
						//System.out.println("CONFLIT CAS 2");
						break;
					}
					// Le debut d'un des cours deja assigner est entre le
					// debut et la fin du nouveau cours
					// (Pas besoin de verifier la fin du cours assigner)
					else if(cilStart > cStart && cilStart < cStop) {
						conflict = true;
						//System.out.println("CONFLIT CAS 3");
						break;
					}
				}
			} // if
		} // while
		
		return conflict;
	}
	
	/**
	 * Detects if teacher already has the maximum number of
	 * course assignments in relation to his role.
	 * Courses of type "LAB" are not considered.
	 * 
	 * 'CHR' role = max of 5
	 * 'PRF' role = max of 3
	 * 
	 * (LOG430 MODIFICATION 4b)
	 * 
	 * @param course
	 * @param teacher
	 * @return boolean
	 */
	public boolean isTeacherFullyScheduled(Course course, Teacher teacher) {
		boolean fullyScheduled = false;
		
		// Courses of type "LAB" are not counted
		if(!course.getType().equalsIgnoreCase("LAB")) {
			boolean done = false;
			// total : anciens + nouveaux de type CRS
			int total = teacher.getCoursesTaughtList().size(); 
			
			CourseList cList = teacher.getCoursesAssigned();
			Course courseInList;
			cList.goToFrontOfList();
			
			while (!done) {
				courseInList = cList.getNextCourse();
				
				if(courseInList == null) {
					done = true;
				}
				// Courses of type "LAB" are not counted
				else if (!courseInList.getType().equalsIgnoreCase("LAB")) {
					total += 1;
				} // if
			} // while
			
			// Teacher is of type "Professor" and is fully scheduled
			if(teacher.getType().equalsIgnoreCase("PRF") && total == 3) {
				fullyScheduled = true;
			}
			// Teacher is of type "Charger de cours" and is fully scheduled
			else if(teacher.getType().equalsIgnoreCase("CHR") && total == 5) {
				fullyScheduled = true;
			}
		}
		
		return fullyScheduled;
	}
}
