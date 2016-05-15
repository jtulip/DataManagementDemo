package datamanagement.entities;

public class Record implements IRecord {
	
	private Integer studentId_;
	private String unitCode_;
	private float asg1Mark_;
	private float asg2Mark_;
	private float examMark_;

	
	
	public Record(Integer studentId, String unitCode, float asg1Mark, float asg2Mark, float examMark) {
		this.studentId_ = studentId;
		this.unitCode_ = unitCode;
		this.asg1Mark_ = asg1Mark;

		this.asg2Mark_ = asg2Mark;
		this.examMark_ = examMark;
	}

	
	
	public Integer getStudentId() {
		return studentId_;
	}

	
	
	public String getSubjectCode() {
		return unitCode_;
	}

	
	
	public void setAsg1Mark(float mark) {
		this.asg1Mark_ = mark;
	}

	
	
	public float getAsg1Mark() {
		return this.asg1Mark_;
	}

	
	
	public void setAsg2Mark(float mark) {
		this.asg2Mark_ = mark;
	}

	
	
	public float getAsg2Mark() {
		return this.asg2Mark_;
	}

	
	
	public void setExamMark(float mark) {
		this.examMark_ = mark;
	}

	
	
	public float getExamMark() {
		return this.examMark_;
	}

	
	
	public float getTotalMark() {
		return asg1Mark_ + asg2Mark_ + examMark_;

	}
}
