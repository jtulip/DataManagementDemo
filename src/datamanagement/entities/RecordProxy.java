package datamanagement.entities;
import datamanagement.daos.IRecordDAO;

public class RecordProxy implements IRecord {
	
	private Integer studentId_;
	private String unitCode_;
	private IRecordDAO recordManager_;

	
	
	public RecordProxy(Integer id, String code, IRecordDAO dao) {
		this.studentId_ = id;
		this.unitCode_ = code;
		this.recordManager_ = dao;
	}

	
	
	public Integer getStudentId() {
		return studentId_;
	}


	
	public String getSubjectCode() {
		return unitCode_;
	}

	
	
	public void setAsg1Mark(float mark) {
		recordManager_.getRecord(studentId_, unitCode_).setAsg1Mark(mark);
	}

	
	
	public float getAsg1Mark() {
		return recordManager_.getRecord(studentId_, unitCode_).getAsg1Mark();
	}

	
	
	public void setAsg2Mark(float mark) {
		recordManager_.getRecord(studentId_, unitCode_).setAsg2Mark(mark);
	}

	
	
	public float getAsg2Mark() {
		return recordManager_.getRecord(studentId_, unitCode_).getAsg2Mark();
	}

	
	
	public void setExamMark(float mark) {
		recordManager_.getRecord(studentId_, unitCode_).setExamMark(mark);
	}

	
	
	public float getExamMark() {
		return recordManager_.getRecord(studentId_, unitCode_).getExamMark();
	}

	
	
	public float getTotalMark() {
		return recordManager_.getRecord(studentId_, unitCode_).getTotalMark();
	}
	
}
