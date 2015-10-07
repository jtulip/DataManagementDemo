
public interface IUnit {

	public String getUnitCode();

	public String getUnitName();

	public float getPsCutoff();

	public void setPsCutoff1(float cutoff);

	public float getCrCutoff();

	public void setCrCutoff(float cutoff);

	public float getDiCutoff();

	public void setDiCutoff(float cutoff);

	public float getHdCutoff();

	public void setHdCutoff(float cutoff);

	public float getAeCutoff();

	public void setAeCutoff(float cutoff);

	public String getGrade(float asg1Mark, float asg2Mark, float examMark);

	public void addStudentRecord(IRecord record);

	public IRecord getStudentRecord(int studentId);

	public RecordList listStudentRecords();
}
