
public class RecordProxy implements IRecord {
	private Integer studentID;
	private String unitCode;
	private RecordManager mngr;

	public RecordProxy(Integer id, String code) {
		this.studentID = id;
		this.unitCode = code;
		this.mngr = RecordManager.getInstance();
	}

	public Integer getStudentID() {
		return studentID;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setAsg1(float mark) {
		mngr.getStudentUnitRecord(studentID, unitCode).setAsg1(mark);
	}

	public float getAsg1() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getAsg1();
	}

	public void setAsg2(float mark) {
		mngr.getStudentUnitRecord(studentID, unitCode).setAsg2(mark);
	}

	public float getAsg2() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getAsg2();
	}

	public void setExam(float mark) {
		mngr.getStudentUnitRecord(studentID, unitCode).setExam(mark);
	}

	public float getExam() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getExam();
	}

	public float getTotal() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getTotal();
	}
}
