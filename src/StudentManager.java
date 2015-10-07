
import org.jdom.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class StudentManager {
	private static StudentManager self = null;
	private StudentMap studentMap_;
	private Map<String, StudentMap> unitMap_;

	
	
	public static StudentManager getInstance() {
		if (self == null) {
			self = new StudentManager();
		}
		return self;
	}

	
	
	private StudentManager() {
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
		Element studentElement = getStudentElement(id);
		if (studentElement != null) {
			RecordList records = RecordManager.getInstance().getRecordsByStudent(id);
			String firstName = studentElement.getAttributeValue("fname");
			String lastName = studentElement.getAttributeValue("lname");
			
			student = new Student(id, firstName, lastName, records);

			studentMap_.put(id, student);
			return student;
		}
		throw new RuntimeException("DBMD: createStudent : student not in file");
	}

	
	
	private IStudent createStudentProxy(Integer id) {
		Element studentElement = getStudentElement(id);
		
		if (studentElement != null) {
			String firstName = studentElement.getAttributeValue("fname");
			String lastName = studentElement.getAttributeValue("lname");
			return new StudentProxy(id, firstName, lastName);
		}
		throw new RuntimeException("DBMD: createStudent : student not in file");
	}

	
	
	public StudentMap getStudentsByUnit(String unitCode) {
		StudentMap studentMap = unitMap_.get(unitCode);
		
		if (studentMap != null) {
			return studentMap;
		}
		
		studentMap = new StudentMap();
		IStudent student;
		RecordList unitRecords = RecordManager.getInstance().getRecordsByUnit(unitCode);
		
		for (IRecord S : unitRecords) {
			student = createStudentProxy(new Integer(S.getStudentID()));
			Integer studentId = student.getID();
			studentMap.put(studentId, student);
		}
		
		unitMap_.put(unitCode, studentMap);
		return studentMap;
	}
	
	
	private Element getStudentElement(Integer id) {
		Document doc = XMLManager.getInstance().getDocument();
		Element studentTableElement = doc.getRootElement().getChild("studentTable");
		
		@SuppressWarnings("unchecked")
		List<Element> studentElements = (List<Element>) studentTableElement.getChildren("student");
		for (Element el : studentElements) {
			if (id.toString().equals(el.getAttributeValue("sid"))) {
				return el;
			}
		}
		return null;
	}

}
