package datamanagement.controllers;

import datamanagement.daos.ISubjectDAO;
import datamanagement.entities.SubjectMap;
import datamanagement.uis.IUnitLister;

public class ListSubjectsCTL {
	
	private ISubjectDAO subjectManager_;

	
	
	public ListSubjectsCTL(ISubjectDAO dao) {
		subjectManager_ = dao;
	}

	
	
	public void listUnits(IUnitLister lister) {
		lister.clearUnits();
		SubjectMap subjects = subjectManager_.getSubjects();
		for (String subjectCode : subjects.keySet())
			lister.addUnit(subjects.get(subjectCode));
	}
}
