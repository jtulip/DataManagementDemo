package datamanagement.daos;

public interface IDataManager {
	
	public void init();
	
	public IStudentDAO getStudentDAO();

	public ISubjectDAO getSubjectDAO();
	
	public IRecordDAO getRecordDAO();
	
	public void close();

}
