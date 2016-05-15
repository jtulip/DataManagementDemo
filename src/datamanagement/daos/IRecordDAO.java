package datamanagement.daos;

import datamanagement.entities.IRecord;
import datamanagement.entities.RecordList;

public interface IRecordDAO {

	public IRecord getRecord(Integer studentID, String subjectCode);

	public RecordList getRecordsBySubject(String subjectCode);

	public RecordList getRecordsByStudent(Integer studentID);

	public void saveRecord(IRecord record);

}
