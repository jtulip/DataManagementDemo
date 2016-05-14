package datamanagement.daos.derby;

import java.util.List;
import java.sql.Connection;

import datamanagement.entities.IUnit;
import datamanagement.entities.RecordList;
import datamanagement.entities.Unit;
import datamanagement.entities.UnitMap;
import datamanagement.entities.UnitProxy;

public class UnitDAO {

	private static UnitDAO self_ = null;
	private UnitMap unitMap_;
	private Connection connection_;

	
	
	public static UnitDAO getInstance() {
		if (self_ == null) {
			self_ = new UnitDAO();
		}
		return self_;
	}

	
	
	private UnitDAO() {
		unitMap_ = new UnitMap();
		connection_ = DataManager.getInstance().getConnection();
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
		return null;
	}

	
	
	public UnitMap getUnits() {

		IUnit unit;
		UnitMap unitMap = new UnitMap();
		return unitMap;
	}

	
	

}
