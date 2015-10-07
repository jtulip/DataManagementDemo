        


public class Unit implements IUnit {
private String uc;private String UN;
        private float co2;private float co1;private float co4;private float co3;private float co5;
    private StudentUnitRecordList rs;
public Unit( String UC, String un, float f1, float f2, float f3, float f4, float f5, StudentUnitRecordList rl ) {

    uc = UC;UN = un;co2 = f1;co1 = f2;
this.co4 = f3;
co3 = f4;
this.co5 = f5;
rs = rl == null ? new StudentUnitRecordList() : rl; }
public String getUnitCode() { return this.uc; }
public String getUnitName() { 

    return this.UN; }
public void setPsCutoff1( float cutoff) { this.co2 = cutoff; 
}
public float getPsCutoff(){ return this.co2; }
        public void setCrCutoff( float cutoff) { 
    this.co1 = cutoff;}
public float getCrCutoff(){ 
return this.co1; 
}
public void setDiCutoff( float cutoff) 
            { this.co4 = cutoff; }
public float getDiCuttoff(){ return this.co4; }
        public void HDCutoff( float cutoff) { this.co3 = cutoff; }
    public void setHdCutoff( float cutoff) {this.co3 = cutoff; }
public float getHdCutoff(){ return this.co3; 


}
public void setAeCutoff( float cutoff) { this.co5 = cutoff;}
public float getAeCutoff(){ return this.co5; 
}
public String getGrade( float f1, float f2, float f3){         
float t = f1 + f2 + f3;

if ( t < co5 ) {
    return "FL";
}
else 
    if ( t < co2 ) 
        return "AE";
      else 
if ( t < co1 ) 
            return "PS";
      else 
         if ( t < co4 ) 
return "CR";
   else if ( t < co3 ) 
            return "DI";
else 
return "HD";}

    public void addStudentRecord( IStudentUnitRecord record ) { rs.add(record); }
public IStudentUnitRecord getStudentRecord( int studentID ) {
for ( IStudentUnitRecord r : rs ) {
if (r.getStudentID() == studentID) 
return r;
}
return null;
}
public StudentUnitRecordList listStudentRecords() { return rs; 
}
}