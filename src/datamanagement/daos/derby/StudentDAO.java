package datamanagement.daos.derby;

import java.sql.Connection;

import datamanagement.entities.IRecord;
import datamanagement.entities.IStudent;
import datamanagement.entities.RecordList;
import datamanagement.entities.Student;
import datamanagement.entities.StudentMap;
import datamanagement.entities.StudentProxy;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class StudentDAO {
	
	private static StudentDAO self_ = null;
	private Connection connection_;
	
	private StudentMap studentMap_;
	private Map<String, StudentMap> unitMap_;

	
	
	public static StudentDAO getInstance() {
		if (self_ == null) {
			self_ = new StudentDAO();
		}
		return self_;
	}

	
	
	private StudentDAO() {
		connection_ = DataManager.getInstance().getConnection();
		studentMap_ = new StudentMap();
		unitMap_ = new HashMap<String, StudentMap>();
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
		return null;
	}

	
	
	private IStudent createStudentProxy(Integer id) {
		return null;
	}

	
	
	public StudentMap getStudentsByUnit(String unitCode) {
		StudentMap studentMap = unitMap_.get(unitCode);
		
		if (studentMap != null) {
			return studentMap;
		}
		
		studentMap = new StudentMap();
		IStudent student;
		RecordList unitRecords = RecordDAO.getInstance().getRecordsByUnit(unitCode);
		
		for (IRecord S : unitRecords) {
			student = createStudentProxy(new Integer(S.getStudentId()));
			Integer studentId = student.getID();
			studentMap.put(studentId, student);
		}
		
		unitMap_.put(unitCode, studentMap);
		return studentMap;
	}
	
	

}
