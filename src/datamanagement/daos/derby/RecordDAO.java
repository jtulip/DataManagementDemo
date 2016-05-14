package datamanagement.daos.derby;

import java.util.List;

import java.sql.Connection;

import datamanagement.entities.IRecord;
import datamanagement.entities.Record;
import datamanagement.entities.RecordList;
import datamanagement.entities.RecordMap;
import datamanagement.entities.RecordProxy;


public class RecordDAO {

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

	
	
	public IRecord getStudentUnitRecord(Integer studentID, String unitCode) {
		String recordKey = studentID.toString() + unitCode;
		IRecord record = recordMap_.get(recordKey);
		
		if (record == null) {
			record = createStudentUnitRecord(studentID, unitCode);
		}
		return record;
	}

	
	
	private IRecord createStudentUnitRecord(Integer studentId, String unitCode) {
		return null;
	}


	
	public RecordList getRecordsByUnit(String unitCode) {
		RecordList recordList = recordsByUnit_.get(unitCode);
		if (recordList != null) {
			return recordList;
		}

		recordList = new RecordList();
		return recordList;
	}

	
	
	public RecordList getRecordsByStudent(Integer studentID) {
		RecordList recordList = recordsByStudent_.get(studentID);
		if (recordList != null) {
			return recordList;
		}
		
		recordList = new RecordList();
		return recordList;
	}

	
	
	public void saveRecord(IRecord record) {

	}

}
