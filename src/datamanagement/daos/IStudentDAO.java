package datamanagement.daos;

import datamanagement.entities.IStudent;
import datamanagement.entities.StudentMap;

public interface IStudentDAO {
	
	public IStudent getStudent(Integer id);

	public StudentMap getStudentsBySubject(String subjectCode);

}
