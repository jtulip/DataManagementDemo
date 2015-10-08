
import java.util.List;
import org.jdom.*;

public class UnitDAO {

	private static UnitDAO self_ = null;
	private UnitMap unitMap_;

	
	
	public static UnitDAO getInstance() {
		if (self_ == null) {
			self_ = new UnitDAO();
		}
		return self_;
	}

	
	
	private UnitDAO() {
		unitMap_ = new UnitMap();
	}

	
	
	public IUnit getUnit(String unitCode) {
		IUnit unit = unitMap_.get(unitCode);
		
		if (unit == null) {
			unit = createUnit(unitCode);
		}
		return unit; 
	}

	
	
	private IUnit createUnit(String unitCode) {

		IUnit unit;
		List<Element> elementList = getElementList("unitTable", "unit");

		for (Element element : elementList) {
			String uid = element.getAttributeValue("uid");
			if (unitCode.equals(uid)) {
				
				String unitName = element.getAttributeValue("name");
				float psCutoff = Float.valueOf(element.getAttributeValue("ps")).floatValue();
				float crCutoff = Float.valueOf(element.getAttributeValue("cr")).floatValue();
				float diCutoff = Float.valueOf(element.getAttributeValue("di")).floatValue();
				float hdCutoff = Float.valueOf(element.getAttributeValue("hd")).floatValue();
				float aeCutoff = Float.valueOf(element.getAttributeValue("ae")).floatValue();
				RecordList records = RecordDAO.getInstance().getRecordsByUnit(unitCode);
				
				unit = new Unit(unitCode, unitName, psCutoff, crCutoff, diCutoff, hdCutoff, aeCutoff, records);
				unitMap_.put(unit.getUnitCode(), unit);
				return unit;
			}
		}
		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}

	
	
	public UnitMap getUnits() {

		IUnit unit;
		UnitMap unitMap = new UnitMap();
		List<Element> elementList = getElementList("unitTable", "unit");

		for (Element element : elementList) {
			String unitCode = element.getAttributeValue("uid");
			String unitName = element.getAttributeValue("name");
			
			unit = new UnitProxy(unitCode, unitName);
			
			// unit maps are filled with PROXY units
			unitMap.put(unit.getUnitCode(), unit);
		} 
		return unitMap;
	}

	
	
	private List<Element> getElementList(String tableId, String attributeId) {
		Document doc = XMLManager.getInstance().getDocument();
		Element recordTableElement = doc.getRootElement().getChild(tableId);
		@SuppressWarnings("unchecked")
		List<Element> elementList = (List<Element>) recordTableElement.getChildren(attributeId);
		return elementList;
	}

}
