package datamanagement.daos.derby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datamanagement.daos.IStudentDAO;
import datamanagement.daos.derby.RecordDAO;
import datamanagement.entities.IStudent;
import datamanagement.entities.RecordList;
import datamanagement.entities.Student;
import datamanagement.entities.StudentMap;
import datamanagement.entities.StudentProxy;

import java.util.Map;
import java.util.HashMap;



public class StudentDAO implements IStudentDAO {
	
	private static StudentDAO self_ = null;
	private Connection connection_;
	
	private StudentMap studentMap_;
	private Map<String, StudentMap> subjectMap_;

	
	
	public static StudentDAO getInstance() {
		if (self_ == null) {
			self_ = new StudentDAO();
		}
		return self_;
	}

	
	
	private StudentDAO() {
		connection_ = DataManager.getInstance().getConnection();
		studentMap_ = new StudentMap();
		subjectMap_ = new HashMap<String, StudentMap>();
	}

	
	
	public IStudent getStudent(Integer id) {
		IStudent student = studentMap_.get(id);
		if (student == null) {
			student = createStudent(id);
		}
		return student;
	}

	
	
	private IStudent createStudent(Integer id) {
		IStudent student;
        String raw = String.format("SELECT * from Students where StudentId = %d", id.intValue());
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            if (res.next()) {
            	Integer studentId = Integer.valueOf(res.getInt("StudentId"));
    			String firstName = res.getString("FirstName");
    			String lastName = res.getString("LastName");
    			RecordList records = RecordDAO.getInstance().getRecordsByStudent(id);
    			student = new Student(studentId, firstName, lastName, records);
            }
            else {
        		throw new RuntimeException("DBMD: createStudentProxy : student not in file");            	
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("StudentDAO : SQLException thrown creating Student");
        }
		return student;
	}

	
	
	private IStudent createStudentProxy(Integer id) {
		StudentProxy proxy;
        String raw = String.format("SELECT FirstName, LastName from Students where StudentId = %d", id.intValue());
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            if (res.next()) {
    			String firstName = res.getString("FirstName");
    			String lastName = res.getString("LastName");
    			proxy = new StudentProxy(id, firstName, lastName, this);
            }
            else {
        		throw new RuntimeException("DBMD: createStudentProxy : student not in file");            	
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("StudentDAO : SQLException thrown creating StudentProxy");
        }
		return proxy;
	}

	
	
	public StudentMap getStudentsBySubject(String subjectCode) {
		StudentMap studentMap = subjectMap_.get(subjectCode);
		
		if (studentMap != null) {
			return studentMap;
		}
		
		studentMap = new StudentMap();
		IStudent student;
		
        String raw = String.format("SELECT StudentId from Records where SubjectCode ='%s'", subjectCode);
        try {
            Statement sta = connection_.createStatement();
            ResultSet res = sta.executeQuery(raw);
            
            while (res.next()) {
            	Integer studentId = Integer.valueOf(res.getInt("StudentId"));
    			student = createStudentProxy(studentId);
    			studentMap.put(studentId, student);
    			
    			subjectMap_.put(subjectCode, studentMap);
            }
        }
        catch (SQLException sqle) {
        	printSQLException(sqle);
        	throw new RuntimeException("StudentDAO : SQLException thrown getting Student by Subject");
        }

		subjectMap_.put(subjectCode, studentMap);
		return studentMap;
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
