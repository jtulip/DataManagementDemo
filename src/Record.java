


public class Record implements IRecord 
{
private Integer studentID;
private String unitCode;
private float asg1;
private float asg2;
private float exam;
public Record( Integer id, String code, float asg1, float asg2, float exam ) 
{
this.studentID = id;
        this.unitCode = code;
    this.asg1 = asg1;
    
    
    
    
                this.asg2 = asg2;
    this.exam = exam;}
    public Integer getStudentID() { 
    return studentID; 
}
    
public String getUnitCode() { 
        return unitCode;}

        public void setAsg1Mark(float mark) { 
            this.asg1 = mark;}
            public float getAsg1() { 
                
    return this.asg1; 
}

public void setAsg2Mark(float mark) { this.asg2 = mark; 




}
    
    public float getAsg2() { 
        return this.asg2;}
        public void setExamMark(float mark) { 
        this.exam = mark;}
        public float getExam() { 
return this.exam; }
public float getTotal() { 
        return asg1 + asg2 + exam; 

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
                                                                                    }}
