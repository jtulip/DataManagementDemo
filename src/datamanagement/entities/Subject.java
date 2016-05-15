package datamanagement.entities;

public class Subject implements ISubject {
	
	private String unitCode_;
	private String unitName_;
	private int psCutoff_;
	private int crCutoff_;
	private int diCutoff_;
	private int hdCutoff_;
	private int aeCutoff_;
	private RecordList records_;

	
	
	public Subject(String unitCode, String unitName, 
			int psCutoff, int crCutoff, int diCutoff, int hdCutoff, int aeCutoff, RecordList rl) {
		
		unitCode_ = unitCode;
		unitName_ = unitName;
		psCutoff_ = psCutoff;
		crCutoff_ = crCutoff;
		diCutoff_ = diCutoff;
		hdCutoff_ = hdCutoff;
		aeCutoff_ = aeCutoff;
		records_ = rl == null ? new RecordList() : rl;
	}

	
	
	public String getSubjectCode() {
		return this.unitCode_;
	}

	
	
	public String getSubjectName() {

		return this.unitName_;
	}

	
	
	public void setPsCutoff1(int cutoff) {
		this.psCutoff_ = cutoff;
	}

	
	
	public int getPsCutoff() {
		return this.psCutoff_;
	}

	
	
	public void setCrCutoff(int cutoff) {
		this.crCutoff_ = cutoff;
	}

	
	
	public int getCrCutoff() {
		return this.crCutoff_;
	}

	
	
	public void setDiCutoff(int cutoff) {
		this.diCutoff_ = cutoff;
	}

	
	
	public int getDiCutoff() {
		return this.diCutoff_;
	}

	
	
	public void setHdCutoff(int cutoff) {
		this.hdCutoff_ = cutoff;
	}

	
	
	public int getHdCutoff() {
		return this.hdCutoff_;

	}

	
	
	public void setAeCutoff(int cutoff) {
		this.aeCutoff_ = cutoff;
	}

	
	
	public int getAeCutoff() {
		return this.aeCutoff_;
	}

	
	
	public String getGrade(float asg1Mark, float asg2Mark, float examMark) {
		float totalMark = asg1Mark + asg2Mark + examMark;

		if (totalMark < aeCutoff_) {
			return "FL";
		} 
		else if (totalMark < psCutoff_) {
			return "AE";
		}
		else if (totalMark < crCutoff_) {
			return "PS";
		}
		else if (totalMark < diCutoff_) {
			return "CR";
		}
		else if (totalMark < hdCutoff_) {
			return "DI";
		}
		else {
			return "HD";
		}
	}

	
	
	public void addStudentRecord(IRecord record) {
		records_.add(record);
	}


	
	public IRecord getStudentRecord(int studentId) {
		for (IRecord record : records_) {
			if (record.getStudentId() == studentId) {
				return record;
			}
		}
		return null;
	}

	
	
	public RecordList listStudentRecords() {
		return records_;
	}
}