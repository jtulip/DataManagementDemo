package datamanagement.controllers;

import datamanagement.daos.IStudentDAO;
import datamanagement.entities.StudentMap;
import datamanagement.uis.IStudentLister;

public class ListStudentsCTL {
	
	private IStudentDAO studentManager_;

	
	
	public ListStudentsCTL(IStudentDAO dao) {
		studentManager_ = dao;
	}

	
	
	public void listStudents(IStudentLister lister, String unitCode) {
		lister.clearStudents();
		StudentMap students = studentManager_.getStudentsBySubject(unitCode);
		for (Integer id : students.keySet())
			lister.addStudent(students.get(id));
	}
}
