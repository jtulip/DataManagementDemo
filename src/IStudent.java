


public interface IStudent {

    public Integer getID();

    public String getFirstName();
    public void setFirstName(String firstName);

    public String getLastName();
    public void setLastName(String lastName);

    public void addUnitRecord( IRecord record );
    public IRecord getUnitRecord( String unitCode );

    public RecordList getUnitRecords();

}
