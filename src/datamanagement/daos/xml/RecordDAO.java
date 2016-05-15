package datamanagement.daos.xml;

import java.util.List;

import org.jdom.*;

import datamanagement.daos.IRecordDAO;
import datamanagement.entities.IRecord;
import datamanagement.entities.Record;
import datamanagement.entities.RecordList;
import datamanagement.entities.RecordMap;
import datamanagement.entities.RecordProxy;

public class RecordDAO implements IRecordDAO {

	private static RecordDAO recordManager_ = null;
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

	
	
	private IRecord createRecord(Integer studentId, String unitCode) {
		
		List<Element> elementList = getElementList("studentUnitRecordTable", "record");

		for (Element el : elementList) {
			String sid = el.getAttributeValue("sid");
			String uid = el.getAttributeValue("uid");
			boolean recordMatches = studentId.toString().equals(sid) && unitCode.equals(uid);
			
			if (recordMatches) {
				float asg1Mark = new Float(el.getAttributeValue("asg1")).floatValue();
				float asg2Mark = new Float(el.getAttributeValue("asg2")).floatValue();
				float examMark = new Float(el.getAttributeValue("exam")).floatValue();
				
				IRecord record = new Record(studentId, unitCode, asg1Mark, asg2Mark, examMark);
				
				String recordKey = record.getStudentId().toString() + record.getSubjectCode();
				recordMap_.put(recordKey, record);
				return record;
			}
		}
		throw new RuntimeException("DBMD: createRecord : record not in file");
	}


	
	public RecordList getRecordsBySubject(String unitCode) {
		RecordList recordList = recordsByUnit_.get(unitCode);
		if (recordList != null) {
			return recordList;
		}

		recordList = new RecordList();
		List<Element> elementList = getElementList("studentUnitRecordTable", "record");
		
		for (Element element : elementList) {
			if (unitCode.equals(element.getAttributeValue("uid"))) {
				Integer studentId = new Integer(element.getAttributeValue("sid"));
				RecordProxy recordProxy = new RecordProxy(studentId, unitCode, this);
				recordList.add(recordProxy);
			}
		}
		
		// be careful - recordList could be empty
		if (recordList.size() > 0) {
			recordsByUnit_.put(unitCode, recordList); 
		}
		return recordList;
	}

	
	
	public RecordList getRecordsByStudent(Integer studentID) {
		RecordList recordList = recordsByStudent_.get(studentID);
		if (recordList != null) {
			return recordList;
		}
		
		recordList = new RecordList();
		List<Element> elementList = getElementList("studentUnitRecordTable", "record");
		
		for (Element element : elementList) {
			if (studentID.toString().equals(element.getAttributeValue("sid"))) {
				Integer studentId = new Integer(element.getAttributeValue("sid"));
				String subjectCode = element.getAttributeValue("uid");
				RecordProxy recordProxy = new RecordProxy(studentId, subjectCode, this);
				recordList.add(recordProxy);
			}
		}
		// be careful - recordList could be empty
		if (recordList.size() > 0) {
			recordsByStudent_.put(studentID, recordList);
		}
		return recordList;
	}

	
	
	public void saveRecord(IRecord record) {
		List<Element> elementList = getElementList("studentUnitRecordTable", "record");
		
		for (Element element : elementList) {
			Integer studentId = Integer.valueOf(record.getStudentId());
			String unitCode = record.getSubjectCode();
			
			boolean recordMatches = studentId.toString().equals(element.getAttributeValue("sid")) && 
									unitCode.equals(element.getAttributeValue("uid"));
			
			if (recordMatches) {
				Float asg1Value = Float.valueOf(record.getAsg1Mark());
				Float asg2Value = Float.valueOf(record.getAsg2Mark());
				Float examValue = Float.valueOf(record.getExamMark());
				
				element.setAttribute("asg1", asg1Value.toString());
				element.setAttribute("asg2", asg2Value.toString());
				element.setAttribute("exam", examValue.toString());

				// write out the XML file for continuous save
				DataManager.getInstance().saveDocument(); 
				return;
			}
		}

		throw new RuntimeException("DBMD: saveRecord : no such record in data");
	}	

	
	
	private List<Element> getElementList(String tableId, String attributeId) {
		Document doc = DataManager.getInstance().getDocument();
		Element recordTableElement = doc.getRootElement().getChild(tableId);
		@SuppressWarnings("unchecked")
		List<Element> elementList = (List<Element>) recordTableElement.getChildren(attributeId);
		return elementList;
	}

}
