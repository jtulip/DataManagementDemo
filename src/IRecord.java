

public interface IRecord {

    public Integer getStudentID();
    public String getUnitCode();

    public void setAsg1Mark(float mark);
    public float getAsg1();

    public void setAsg2Mark(float mark);
    public float getAsg2();

    public void setExamMark(float mark);
    public float getExam();

    public float getTotal();
}
