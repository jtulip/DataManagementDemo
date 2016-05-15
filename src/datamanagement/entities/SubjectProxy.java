package datamanagement.entities;

import datamanagement.daos.ISubjectDAO;

public class SubjectProxy implements ISubject {
	private String UC;
	private String un;

	private ISubjectDAO subjectManager_;

	
	
	public SubjectProxy(String unitCode, String unitName, ISubjectDAO dao) {
		this.UC = unitCode;
		this.un = unitName;
		subjectManager_ = dao;
	}

	
	
	public String getSubjectCode() {
		return this.UC;
	}

	
	
	public String getSubjectName() {
		return this.un;
	}

	
	
	public void setPsCutoff1(int cutoff) {
		subjectManager_.getSubject(UC).setPsCutoff1(cutoff);
	}


	
	public int getPsCutoff() {
		return subjectManager_.getSubject(UC).getPsCutoff();
	}


	
	public void setCrCutoff(int cutoff) {
		subjectManager_.getSubject(UC).setCrCutoff(cutoff);
	}

	
	
	public int getCrCutoff() {
		return subjectManager_.getSubject(UC).getCrCutoff();
	}

	
	
	public void setDiCutoff(int cutoff) {
		subjectManager_.getSubject(UC).setDiCutoff(cutoff);
	}

	
	
	public int getDiCutoff() {
		return subjectManager_.getSubject(UC).getDiCutoff();
	}

	
	
	public void setHdCutoff(int cutoff) {
		subjectManager_.getSubject(UC).setHdCutoff(cutoff);
	}

	
	
	public int getHdCutoff() {

		return subjectManager_.getSubject(UC).getHdCutoff();
	}

	
	
	public void setAeCutoff(int cutoff) {
		subjectManager_.getSubject(UC).setAeCutoff(cutoff);
	}

	
	
	public int getAeCutoff() {
		return subjectManager_.getSubject(UC).getAeCutoff();
	}

	
	
	public String getGrade(float f1, float f2, float f3) {
		return subjectManager_.getSubject(UC).getGrade(f1, f2, f3);
	}

	
	
	public void addStudentRecord(IRecord record) {
		subjectManager_.getSubject(UC).addStudentRecord(record);
	}

	
	

	public IRecord getStudentRecord(int s) {
		return subjectManager_.getSubject(UC).getStudentRecord(s);
	}

	
	
	public RecordList listStudentRecords() {
		return subjectManager_.getSubject(UC).listStudentRecords();
	}
}
