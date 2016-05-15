package datamanagement.daos.xml;

import java.util.List;
import org.jdom.*;

import datamanagement.daos.ISubjectDAO;
import datamanagement.entities.ISubject;
import datamanagement.entities.RecordList;
import datamanagement.entities.Subject;
import datamanagement.entities.SubjectMap;
import datamanagement.entities.SubjectProxy;



public class SubjectDAO implements ISubjectDAO {

	private static SubjectDAO self_ = null;
	private SubjectMap subjectMap_;

	
	
	public static SubjectDAO getInstance() {
		if (self_ == null) {
			self_ = new SubjectDAO();
		}
		return self_;
	}

	
	
	private SubjectDAO() {
		subjectMap_ = new SubjectMap();
	}

	
	
	public ISubject getSubject(String unitCode) {
		ISubject unit = subjectMap_.get(unitCode);
		
		if (unit == null) {
			unit = createSubject(unitCode);
		}
		return unit; 
	}

	
	
	private ISubject createSubject(String subjectCode) {

		ISubject subject;
		List<Element> elementList = getElementList("unitTable", "unit");

		for (Element element : elementList) {
			String uid = element.getAttributeValue("uid");
			if (subjectCode.equals(uid)) {
				
				String subjectName = element.getAttributeValue("name");
				int psCutoff = Integer.valueOf(element.getAttributeValue("ps")).intValue();
				int crCutoff = Integer.valueOf(element.getAttributeValue("cr")).intValue();
				int diCutoff = Integer.valueOf(element.getAttributeValue("di")).intValue();
				int hdCutoff = Integer.valueOf(element.getAttributeValue("hd")).intValue();
				int aeCutoff = Integer.valueOf(element.getAttributeValue("ae")).intValue();
				RecordList records = RecordDAO.getInstance().getRecordsBySubject(subjectCode);
				
				subject = new Subject(subjectCode, subjectName, psCutoff, crCutoff, diCutoff, hdCutoff, aeCutoff, records);
				subjectMap_.put(subject.getSubjectCode(), subject);
				return subject;
			}
		}
		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}

	
	
	public SubjectMap getSubjects() {

		ISubject subject;
		SubjectMap subjectMap = new SubjectMap();
		List<Element> elementList = getElementList("unitTable", "unit");

		for (Element element : elementList) {
			String subjectCode = element.getAttributeValue("uid");
			String subjectName = element.getAttributeValue("name");
			
			subject = new SubjectProxy(subjectCode, subjectName, this);
			
			// subject maps are filled with PROXY subjects
			subjectMap.put(subject.getSubjectCode(), subject);
		} 
		return subjectMap;
	}

	
	
	private List<Element> getElementList(String tableId, String attributeId) {
		Document doc = DataManager.getInstance().getDocument();
		Element recordTableElement = doc.getRootElement().getChild(tableId);
		@SuppressWarnings("unchecked")
		List<Element> elementList = (List<Element>) recordTableElement.getChildren(attributeId);
		return elementList;
	}

}
