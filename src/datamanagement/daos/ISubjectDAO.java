package datamanagement.daos;

import datamanagement.entities.ISubject;
import datamanagement.entities.SubjectMap;

public interface ISubjectDAO {
	
	public ISubject getSubject(String subjectCode);
	
	public SubjectMap getSubjects();

}
