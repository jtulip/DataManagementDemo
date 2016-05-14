package datamanagement.uis;
import datamanagement.entities.IStudent;

/**
 * @author jtulip
 */

public interface IStudentLister {

	public void clearStudents();

	public void addStudent(IStudent student);
}
