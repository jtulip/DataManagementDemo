
public class RecordProxy implements IRecord {
	
	private Integer studentId_;
	private String unitCode_;
	private RecordDAO recordManager_;

	
	
	public RecordProxy(Integer id, String code) {
		this.studentId_ = id;
		this.unitCode_ = code;
		this.recordManager_ = RecordDAO.getInstance();
	}

	
	
	public Integer getStudentId() {
		return studentId_;
	}


	
	public String getUnitCode() {
		return unitCode_;
	}

	
	
	public void setAsg1Mark(float mark) {
		recordManager_.getStudentUnitRecord(studentId_, unitCode_).setAsg1Mark(mark);
	}

	
	
	public float getAsg1Mark() {
		return recordManager_.getStudentUnitRecord(studentId_, unitCode_).getAsg1Mark();
	}

	
	
	public void setAsg2Mark(float mark) {
		recordManager_.getStudentUnitRecord(studentId_, unitCode_).setAsg2Mark(mark);
	}

	
	
	public float getAsg2Mark() {
		return recordManager_.getStudentUnitRecord(studentId_, unitCode_).getAsg2Mark();
	}

	
	
	public void setExamMark(float mark) {
		recordManager_.getStudentUnitRecord(studentId_, unitCode_).setExamMark(mark);
	}

	
	
	public float getExamMark() {
		return recordManager_.getStudentUnitRecord(studentId_, unitCode_).getExamMark();
	}

	
	
	public float getTotalMark() {
		return recordManager_.getStudentUnitRecord(studentId_, unitCode_).getTotalMark();
	}
	
}
