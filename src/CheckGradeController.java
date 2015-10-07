


public class CheckGradeController {

CheckGradeUserInterface cgUI;
    String cuc = null;
Integer currentStudentID = null;
    public CheckGradeController() {}

    
    
    public void execute() {
cgUI = new CheckGradeUserInterface( this );
        cgUI.setState1(false);
        
        
        
    cgUI.setState2(false);
            cgUI.setState3(false);
cgUI.setState4(false);
    cgUI.setState5(false);
        cgUI.Refresh3();

            ListUnitsCTL luCTL = new ListUnitsCTL();
luCTL.listUnits( cgUI );
                cgUI.setVisible(true);
                cgUI.setState1(true);}
        public void unitSelected( String code ) {
            
            
        if (code.equals("NONE")) 
            cgUI.setState2(false);
        else {
            ListStudentsCTL lsCTL = new ListStudentsCTL();
            lsCTL.listStudents(cgUI, code);
            cuc = code;
            cgUI.setState2(true);}
            cgUI.setState3(false);
        }


        
public void studentSelected( Integer id ) {
        currentStudentID = id;
   if ( currentStudentID.intValue() == 0 ) {
            cgUI.Refresh3();
    cgUI.setState3(false);cgUI.setState4(false);
    
    
        cgUI.setState5(false);}
   
   
   
   
  else 
{
    IStudent s = StudentManager.getInstance().getStudent(id);
    
    
IRecord r = s.getUnitRecord(cuc);
    
    
    
    
            cgUI.setRecord(r);
            cgUI.setState3(true);
            cgUI.setState4(true);
            cgUI.setState5(false);
        
}}

            public String checkGrade( float f, float g, float h ) {
                IUnit u = UnitManager.UM().getUnit(cuc);
        String s = u.getGrade( f, g, h);
        return s;
    }
public void enableChangeMarks() {
    
    
        cgUI.setState4(false); cgUI.setState5(true);}
        public void saveGrade( float asg1, float asg2, float exam ) {
            
            
            
            
            
        IUnit u = UnitManager.UM().getUnit(cuc);
        IStudent s = StudentManager.getInstance().getStudent(currentStudentID);
        
        
        IRecord r = s.getUnitRecord(cuc);
r.setAsg1(asg1);
                    r.setAsg2(asg2);
r.setExam(exam);
            RecordManager.getInstance().saveRecord(r);
cgUI.setState4(true);



cgUI.setState5(false);




















                        
                                                                                                }}
