package datamanagement.daos.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datamanagement.daos.IRecordDAO;
import datamanagement.entities.IRecord;
import datamanagement.entities.Record;
import datamanagement.entities.RecordList;
import datamanagement.entities.RecordMap;
import datamanagement.entities.RecordProxy;


public class RecordDAO implements IRecordDAO {

	private static RecordDAO recordManager_ = null;
	private Connection connection_;
	
	private RecordMap recordMap_;
	private java.util.HashMap<String, RecordList> recordsByUnit_;
	private java.util.HashMap<Integer, RecordList> recordsByStudent_;

	
	
	public static RecordDAO getInstance() {
		if (recordManager_ == null) {
			recordManager_ = new RecordDAO();
		}
		return recordManager_;
	}

	
	
	private RecordDAO() {
		connection_ = DataManager.getInstance().getConnection();
		recordMap_ = new RecordMap();
		recordsByUnit_ = new java.util.HashMap<>();
		recordsByStudent_ = new java.util.HashMap<>();
	}

	
	
	public IRecord getRecord(Integer studentID, String unitCode) {
		String recordKey = studentID.toString() + unitCode;
		IRecord record = recordMap_.get(recordKey);
		
		if (record == null) {
			record = createRecord(studentID, unitCode);
		}
		return record;
	}

	
	
	private IRecord createRecord(Integer studentId, String subjectCode) {
		IRecord record = null;
        String raw = String.format("SELECT * from Records where SubjectCode ='%s' and StudentId = %d", subjectCode, studentId);
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            if (res.next()) {
				float asg1Mark = res.getFloat("Asg1Mark");
				float asg2Mark = res.getFloat("Asg2Mark");
				float examMark = res.getFloat("ExamMark");
				
				record = new Record(studentId, subjectCode, asg1Mark, asg2Mark, examMark);
				
				String recordKey = record.getStudentId().toString() + record.getSubjectCode();
				recordMap_.put(recordKey, record);
            }
            else {
        		throw new RuntimeException("DBMD: createRecord : record not in file");
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("RecordDAO : SQLException thrown creating Record");
        }
        return record;
	}


	
	public RecordList getRecordsBySubject(String subjectCode) {
		RecordList recordList = recordsByUnit_.get(subjectCode);
		if (recordList != null) {
			return recordList;
		}

		recordList = new RecordList();
        String raw = String.format("SELECT StudentId from Records where SubjectCode ='%s'", subjectCode);
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            while (res.next()) {
            	Integer studentId = Integer.valueOf(res.getInt("StudentId"));
				RecordProxy recordProxy = new RecordProxy(studentId, subjectCode, this);
				recordList.add(recordProxy);
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("RecordDAO : SQLException thrown getting records by Subject");
        }
		return recordList;
	}

	
	
	public RecordList getRecordsByStudent(Integer studentId) {
		RecordList recordList = recordsByStudent_.get(studentId);
		if (recordList != null) {
			return recordList;
		}
		
		recordList = new RecordList();
        String raw = String.format("SELECT SubjectCode from Records where StudentId = %d", studentId.intValue());
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            while (res.next()) {
            	String subjectCode = res.getString("SubjectCode");
				RecordProxy recordProxy = new RecordProxy(studentId, subjectCode, this);
				recordList.add(recordProxy);
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("RecordDAO : SQLException thrown getting records by Student");
        }

		return recordList;
	}

	
	
	public void saveRecord(IRecord record) {
		String subjectCode = record.getSubjectCode();
		int studentId = record.getStudentId().intValue();
		float asg1Mark = record.getAsg1Mark();
		float asg2Mark = record.getAsg2Mark();
		float examMark = record.getExamMark();
		
        //update record
        String raw = "UPDATE Records SET Asg1Mark = ?, Asg2Mark = ?, ExamMark = ? WHERE StudentId = ? AND SubjectCode = ?";
        try {
            PreparedStatement sta = connection_.prepareStatement(raw, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           	sta.setFloat(1, asg1Mark);
           	sta.setFloat(2, asg2Mark);
           	sta.setFloat(3, examMark);
           	sta.setInt(4, studentId);
           	sta.setString(5, subjectCode);

            sta.execute();            
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("RecordDAO : SQLException thrown saving record");
        }
		

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
