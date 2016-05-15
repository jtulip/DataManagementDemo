package datamanagement.entities;

public interface IRecord {

	public Integer getStudentId();

	public String getSubjectCode();

	public void setAsg1Mark(float mark);

	public float getAsg1Mark();

	public void setAsg2Mark(float mark);

	public float getAsg2Mark();

	public void setExamMark(float mark);

	public float getExamMark();

	public float getTotalMark();
}
