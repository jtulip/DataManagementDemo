package datamanagement.daos.derby;

import org.apache.derby.jdbc.*;

import java.io.IOException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;

import datamanagement.AppProperties;
import datamanagement.entities.RecordList;
import datamanagement.entities.RecordMap;


public class DataManager {

	private static DataManager derbyManager_ = null;
	private static Connection connection_ = null;

	
	
	public static DataManager getInstance() {
		if (derbyManager_ == null) {
			derbyManager_ = new DataManager();
		}
		return derbyManager_;
	}

	
	
	private DataManager() {
		init();
	}


	public void init() {
		Properties properties = AppProperties.getInstance().getProperties();
		String dbName = properties.getProperty("DBNAME");
		
		try {
			Properties props = new Properties();
			String conn_str = "jdbc:derby:DM_Demo;";
			connection_ = DriverManager.getConnection(conn_str, props);
			System.out.println("Connected to " + dbName );
		}
		catch (Exception e) {
			System.err.printf("%s", "DBMD: DataManager : init : caught Exception\n");
			e.printStackTrace();
			throw new RuntimeException("DBMD: DataManager : init : caught Exception");
		} 
	}
	
	public Connection getConnection() {
		return connection_;
	}
	
	public void close() {
		System.out.println("DataManager closing");
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
                while (se != null) {
	                System.err.println("\n----- SQLException -----");
	                System.err.println("  SQL State:  " + se.getSQLState());
	                System.err.println("  Error Code: " + se.getErrorCode());
	                System.err.println("  Message:    " + se.getMessage());
	                // for stack traces, refer to derby.log or uncomment this:
	                //e.printStackTrace(System.err);
	                se = se.getNextException();
                }
            }
        }
        finally {
        	try {
        		if (connection_ != null) {
        			connection_.close();
        		}
        	}
        	catch (SQLException se) {
                while (se != null) {
	                System.err.println("\n----- SQLException -----");
	                System.err.println("  SQL State:  " + se.getSQLState());
	                System.err.println("  Error Code: " + se.getErrorCode());
	                System.err.println("  Message:    " + se.getMessage());
	                // for stack traces, refer to derby.log or uncomment this:
	                //e.printStackTrace(System.err);
	                se = se.getNextException();
                }
        	}
        }
	}


}
