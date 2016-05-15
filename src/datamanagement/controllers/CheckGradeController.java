package datamanagement.controllers;

import datamanagement.daos.*;
import datamanagement.daos.derby.DataManager;

import datamanagement.entities.IRecord;
import datamanagement.entities.IStudent;
import datamanagement.entities.ISubject;
import datamanagement.uis.CheckGradeUserInterface;

public class CheckGradeController {

	CheckGradeUserInterface checkGradeUI_;
	String currentUnitCode_ = null;
	Integer currentStudentID_ = null;
	ISubjectDAO subjectDAO_ = null;
	IStudentDAO studentDAO_ = null;
	IRecordDAO recordDAO_ = null;

	
	
	public CheckGradeController() {}

	

	public void execute() {
		DataManager dm = DataManager.getInstance();
		dm.init();
		subjectDAO_ = dm.getSubjectDAO();
		studentDAO_ = dm.getStudentDAO();
		recordDAO_  = dm.getRecordDAO();
		
		checkGradeUI_ = new CheckGradeUserInterface(this);
		checkGradeUI_.setUnitComboBoxEnabled(false);

		checkGradeUI_.setStudentComboBoxEnabled(false);
		checkGradeUI_.setCheckButtonEnabled(false);
		checkGradeUI_.setChangeButtonEnabled(false);
		checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);
		checkGradeUI_.resetAllFields();

		ListSubjectsCTL luCTL = new ListSubjectsCTL(subjectDAO_);
		luCTL.listUnits(checkGradeUI_);
		checkGradeUI_.setVisible(true);
		checkGradeUI_.setUnitComboBoxEnabled(true);
	}

	
	
	public void unitSelected(String unitCode) {

		if (unitCode.equals("NONE")) {
			checkGradeUI_.setStudentComboBoxEnabled(false);
		}
		else {
			ListStudentsCTL lsCTL = new ListStudentsCTL(studentDAO_);
			lsCTL.listStudents(checkGradeUI_, unitCode);
			currentUnitCode_ = unitCode;
			checkGradeUI_.setStudentComboBoxEnabled(true);
		}
		checkGradeUI_.setCheckButtonEnabled(false);
	}

	
	
	public void studentSelected(Integer studentId) {
		currentStudentID_ = studentId;
		
		if (currentStudentID_.intValue() == 0) {
			checkGradeUI_.resetAllFields();
			checkGradeUI_.setCheckButtonEnabled(false);
			checkGradeUI_.setChangeButtonEnabled(false);
			checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);
		}

		else {
			IStudent student = studentDAO_.getStudent(studentId);

			IRecord record = student.getRecordForUnit(currentUnitCode_);

			checkGradeUI_.setRecord(record);
			checkGradeUI_.setCheckButtonEnabled(true);
			checkGradeUI_.setChangeButtonEnabled(true);
			checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);

		}
	}

	
	
	public String checkGrade(float asg1Mark, float asg2Mark, float examMark) {
		ISubject unit = subjectDAO_.getSubject(currentUnitCode_);
		String grade = unit.getGrade(asg1Mark, asg2Mark, examMark);
		return grade;
	}

	
	
	public void enableChangeMarks() {
		checkGradeUI_.setChangeButtonEnabled(false);
		checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(true);
	}

	
	
	public void saveGrade(float asg1Mark, float asg2Mark, float examMark) {
		IStudent student = studentDAO_.getStudent(currentStudentID_);

		IRecord record = student.getRecordForUnit(currentUnitCode_);
		record.setAsg1Mark(asg1Mark);
		record.setAsg2Mark(asg2Mark);
		record.setExamMark(examMark);
		recordDAO_.saveRecord(record);

		checkGradeUI_.setChangeButtonEnabled(true);
		checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);
	}

	
	
	public void close() {
		DataManager.getInstance().close();
	}
	
}
