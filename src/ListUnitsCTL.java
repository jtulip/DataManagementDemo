
public class ListUnitsCTL {
	
	private UnitManager unitManager_;

	
	
	public ListUnitsCTL() {
		unitManager_ = UnitManager.getInstance();
	}

	
	
	public void listUnits(IUnitLister lister) {
		lister.clearUnits();
		UnitMap units = unitManager_.getUnits();
		for (String unitCode : units.keySet())
			lister.addUnit(units.get(unitCode));
	}
}
