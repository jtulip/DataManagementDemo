package datamanagement.entities;

public interface ISubject {

	public String getSubjectCode();

	public String getSubjectName();

	public int getPsCutoff();

	public void setPsCutoff1(int cutoff);

	public int getCrCutoff();

	public void setCrCutoff(int cutoff);

	public int getDiCutoff();

	public void setDiCutoff(int cutoff);

	public int getHdCutoff();

	public void setHdCutoff(int cutoff);

	public int getAeCutoff();

	public void setAeCutoff(int cutoff);

	public String getGrade(float asg1Mark, float asg2Mark, float examMark);

	public void addStudentRecord(IRecord record);

	public IRecord getStudentRecord(int studentId);

	public RecordList listStudentRecords();
}
