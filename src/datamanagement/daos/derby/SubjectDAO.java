package datamanagement.daos.derby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datamanagement.daos.ISubjectDAO;
import datamanagement.daos.derby.RecordDAO;
import datamanagement.entities.ISubject;
import datamanagement.entities.RecordList;
import datamanagement.entities.Subject;
import datamanagement.entities.SubjectMap;
import datamanagement.entities.SubjectProxy;



public class SubjectDAO implements ISubjectDAO {

	private static SubjectDAO self_ = null;
	private SubjectMap subjectMap_;
	private Connection connection_;

	
	
	public static SubjectDAO getInstance() {
		if (self_ == null) {
			self_ = new SubjectDAO();
		}
		return self_;
	}

	
	
	private SubjectDAO() {
		connection_ = DataManager.getInstance().getConnection();
		subjectMap_ = new SubjectMap();
	}

	
	
	public ISubject getSubject(String subjectCode) {
		ISubject subject = subjectMap_.get(subjectCode);
		
		if (subject == null) {
			subject = createSubject(subjectCode);
		}
		return subject; 
	}

	
	
	private ISubject createSubject(String subjectCode) {
		ISubject subject;
        String raw = String.format("SELECT * FROM Subjects where SubjectCode = '%s'", subjectCode);
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            if (res.next()) {
            	//String subjectCode = res.getString("SubjectCode");
            	String subjectName = res.getString("SubjectName");
				int psCutoff = res.getInt("PSCutoff");
				int crCutoff = res.getInt("CRCutoff");
				int diCutoff = res.getInt("DICutoff");
				int hdCutoff = res.getInt("HDCutoff");
				int aeCutoff = res.getInt("AECutoff");
				
				//get the associated records from the RecordDAO
				RecordList records = RecordDAO.getInstance().getRecordsBySubject(subjectCode);

				subject = new Subject(subjectCode, subjectName, psCutoff, crCutoff, diCutoff, hdCutoff, aeCutoff, records);
				subjectMap_.put(subject.getSubjectCode(), subject);
				return subject;
            }
            else {
        		throw new RuntimeException("DBMD: createSubject : subject not in file");
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("SubjectDAO : SQLException thrown creating Subject");
        }
		
	}

	
	
	public SubjectMap getSubjects() {

		ISubject subject;
		SubjectMap subjectMap = new SubjectMap();
        String raw = String.format("SELECT SubjectCode, SubjectName FROM Subjects");
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            while (res.next()) {
            	String subjectCode = res.getString("SubjectCode");
            	String subjectName = res.getString("SubjectName");

    			subject = new SubjectProxy(subjectCode, subjectName, this);
    			// subject maps are filled with PROXY subjects
    			subjectMap.put(subject.getSubjectCode(), subject);
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("SubjectDAO : SQLException thrown getting Subjects");
        }
		
		return subjectMap;
	}

	

	public void printSQLException (SQLException sqle) {
        while (sqle != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + sqle.getSQLState());
            System.err.println("  Error Code: " + sqle.getErrorCode());
            System.err.println("  Message:    " + sqle.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            sqle = sqle.getNextException();
        }
	}

}
