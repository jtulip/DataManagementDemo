package datamanagement.controllers;

import datamanagement.daos.derby.StudentDAO;
import datamanagement.entities.StudentMap;
import datamanagement.uis.IStudentLister;

public class ListStudentsCTL {
	
	private StudentDAO studentManager_;

	
	
	public ListStudentsCTL() {
		studentManager_ = StudentDAO.getInstance();
	}

	
	
	public void listStudents(IStudentLister lister, String unitCode) {
		lister.clearStudents();
		StudentMap students = studentManager_.getStudentsByUnit(unitCode);
		for (Integer id : students.keySet())
			lister.addStudent(students.get(id));
	}
}