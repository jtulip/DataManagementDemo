package datamanagement;
import datamanagement.controllers.CheckGradeController;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		try {
			new CheckGradeController().execute();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
