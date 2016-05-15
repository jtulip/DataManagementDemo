package datamanagement.daos.derby;

//import org.apache.derby.jdbc.*;

import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import datamanagement.AppProperties;
import datamanagement.daos.*;


public class DataManager implements IDataManager {

	private static DataManager derbyManager_ = null;
	private static Connection  connection_   = null;

	private static IStudentDAO studentDAO_ = null;
	private static ISubjectDAO subjectDAO_ = null;
	private static IRecordDAO  recordDAO_  = null;
	
	
	public static DataManager getInstance() {
		if (derbyManager_ == null) {
			derbyManager_ = new DataManager();
		}
		return derbyManager_;
	}

	
	
	private DataManager() {}


	public void init() {
		Properties properties = AppProperties.getInstance().getProperties();
		String dbName = properties.getProperty("DBNAME");
		
		try {
			Properties props = new Properties();
	        //props.put("user", "user1");
	        //props.put("password", "user1");
			String conn_str = "jdbc:derby:DM_Demo;";
			connection_ = DriverManager.getConnection(conn_str, props);
			System.out.println("Connected to " + dbName );
			
			studentDAO_ = StudentDAO.getInstance();
			subjectDAO_ = SubjectDAO.getInstance();
			recordDAO_  = RecordDAO.getInstance();			

		}
        catch (SQLException sqle) {
        	printSQLException(sqle);
			throw new RuntimeException("DBMD: DataManager : init : caught SQLException");
        }
	}
	
	
	
	public Connection getConnection() {
		return connection_;
	}

	
	@Override
	public IStudentDAO getStudentDAO() {
		return studentDAO_;
	}



	@Override
	public ISubjectDAO getSubjectDAO() {
		return subjectDAO_;
	}



	@Override
	public IRecordDAO getRecordDAO() {
		return recordDAO_;
	}
	
	public void close() {
		System.out.println("Derby DataManager closing");
        try {
            // the shutdown=true attribute shuts down Derby
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }
        catch (SQLException se) {
            if (( (se.getErrorCode() == 50000)
                    && ("XJ015".equals(se.getSQLState()) ))) {
                System.out.println("Derby shut down normally");
            } 
            else {
                System.err.println("Derby did not shut down normally");
        		printSQLException(se);
            }
        }
        finally {
        	try {
        		if (connection_ != null) {
        			connection_.close();
        		}
        	}
        	catch (SQLException se) {
        		printSQLException(se);
        	}
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
