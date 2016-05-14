package datamanagement.controllers;
import datamanagement.daos.derby.UnitDAO;
import datamanagement.entities.UnitMap;
import datamanagement.uis.IUnitLister;

public class ListUnitsCTL {
	
	private UnitDAO unitManager_;

	
	
	public ListUnitsCTL() {
		unitManager_ = UnitDAO.getInstance();
	}

	
	
	public void listUnits(IUnitLister lister) {
		lister.clearUnits();
		UnitMap units = unitManager_.getUnits();
		for (String unitCode : units.keySet())
			lister.addUnit(units.get(unitCode));
	}
}
