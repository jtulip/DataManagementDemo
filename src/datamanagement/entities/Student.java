package datamanagement.entities;

public class Student implements IStudent {
	private Integer id;
	private String fn;
	private String ln;
	private RecordList su;

	public Student(Integer id, String fn, String ln, RecordList su) {
		this.id = id;
		this.fn = fn;
		this.ln = ln;
		this.su = su == null ? new RecordList() : su;
	}

	public Integer getID() {
		return this.id;
	}

	public String getFirstName() {
		return fn;
	}

	public void setFirstName(String firstName) {
		this.fn = firstName;
	}

	public String getLastName() {
		return ln;
	}

	public void setLastName(String lastName) {

		this.ln = lastName;
	}

	public void addRecord(IRecord record) {
		su.add(record);
	}

	public IRecord getRecordForUnit(String unitCode) {
		for (IRecord r : su)
			if (r.getSubjectCode().equals(unitCode))
				return r;

		return null;

	}

	public RecordList getUnitRecords() {
		return su;
	}
}
