
public class StudentProxy implements IStudent {
	
	private Integer studentId_;
	private String firstName_;

	private String lastName_;
	private StudentDAO studentManager_;

	public StudentProxy(Integer studentId, String firstName, String lastName) {
		this.studentId_ = studentId;
		this.firstName_ = firstName;
		this.lastName_ = lastName;
		this.studentManager_ = StudentDAO.getInstance();
	}

	
	
	public Integer getID() {
		return studentId_;
	}

	
	
	public String getFirstName() {
		return firstName_;
	}

	
	
	public void setFirstName(String firstName) {

		studentManager_.getStudent(studentId_).setFirstName(firstName);
	}

	
	
	public String getLastName() {
		return lastName_;
	}

	
	
	public void setLastName(String lastName) {
		studentManager_.getStudent(studentId_).setLastName(lastName);
	}

	
	
	public void addRecord(IRecord record) {
		studentManager_.getStudent(studentId_).addRecord(record);
	}

	
	
	public IRecord getRecordForUnit(String unitCode) {
		return studentManager_.getStudent(studentId_).getRecordForUnit(unitCode);
	}

	
	
	public RecordList getUnitRecords() {
		return studentManager_.getStudent(studentId_).getUnitRecords();
	}
	
}
