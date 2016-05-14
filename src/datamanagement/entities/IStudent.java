package datamanagement.entities;

public interface IStudent {

	public Integer getID();

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public void addRecord(IRecord record);

	public IRecord getRecordForUnit(String unitCode);

	public RecordList getUnitRecords();

}
