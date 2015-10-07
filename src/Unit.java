
public class Unit implements IUnit {
	
	private String unitCode_;
	private String unitName_;
	private float psCutoff_;
	private float crCutoff_;
	private float diCutoff_;
	private float hdCutoff_;
	private float aeCutoff_;
	private RecordList records_;

	
	
	public Unit(String unitCode, String unitName, 
			    float psCutoff, float crCutoff, float diCutoff, float hdCutoff, float aeCutoff, RecordList rl) {
		
		unitCode_ = unitCode;
		unitName_ = unitName;
		psCutoff_ = psCutoff;
		crCutoff_ = crCutoff;
		diCutoff_ = diCutoff;
		hdCutoff_ = hdCutoff;
		aeCutoff_ = aeCutoff;
		records_ = rl == null ? new RecordList() : rl;
	}

	
	
	public String getUnitCode() {
		return this.unitCode_;
	}

	
	
	public String getUnitName() {

		return this.unitName_;
	}

	
	
	public void setPsCutoff1(float cutoff) {
		this.psCutoff_ = cutoff;
	}

	
	
	public float getPsCutoff() {
		return this.psCutoff_;
	}

	
	
	public void setCrCutoff(float cutoff) {
		this.crCutoff_ = cutoff;
	}

	
	
	public float getCrCutoff() {
		return this.crCutoff_;
	}

	
	
	public void setDiCutoff(float cutoff) {
		this.diCutoff_ = cutoff;
	}

	
	
	public float getDiCutoff() {
		return this.diCutoff_;
	}

	
	
	public void setHdCutoff(float cutoff) {
		this.hdCutoff_ = cutoff;
	}

	
	
	public float getHdCutoff() {
		return this.hdCutoff_;

	}

	
	
	public void setAeCutoff(float cutoff) {
		this.aeCutoff_ = cutoff;
	}

	
	
	public float getAeCutoff() {
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