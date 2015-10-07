
public class CheckGradeController {

	CheckGradeUserInterface checkGradeUI_;
	String currentUnitCode_ = null;
	Integer currentStudentID_ = null;

	
	
	public CheckGradeController() {}

	

	public void execute() {
		checkGradeUI_ = new CheckGradeUserInterface(this);
		checkGradeUI_.setUnitComboBoxEnabled(false);

		checkGradeUI_.setStudentComboBoxEnabled(false);
		checkGradeUI_.setCheckButtonEnabled(false);
		checkGradeUI_.setChangeButtonEnabled(false);
		checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);
		checkGradeUI_.resetAllFields();

		ListUnitsCTL luCTL = new ListUnitsCTL();
		luCTL.listUnits(checkGradeUI_);
		checkGradeUI_.setVisible(true);
		checkGradeUI_.setUnitComboBoxEnabled(true);
	}

	
	
	public void unitSelected(String unitCode) {

		if (unitCode.equals("NONE")) {
			checkGradeUI_.setStudentComboBoxEnabled(false);
		}
		else {
			ListStudentsCTL lsCTL = new ListStudentsCTL();
			lsCTL.listStudents(checkGradeUI_, unitCode);
			currentUnitCode_ = unitCode;
			checkGradeUI_.setStudentComboBoxEnabled(true);
		}
		checkGradeUI_.setCheckButtonEnabled(false);
	}

	
	
	public void studentSelected(Integer studentId) {
		currentStudentID_ = studentId;
		
		if (currentStudentID_.intValue() == 0) {
			checkGradeUI_.resetAllFields();
			checkGradeUI_.setCheckButtonEnabled(false);
			checkGradeUI_.setChangeButtonEnabled(false);
			checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);
		}

		else {
			IStudent student = StudentManager.getInstance().getStudent(studentId);

			IRecord record = student.getUnitRecord(currentUnitCode_);

			checkGradeUI_.setRecord(record);
			checkGradeUI_.setCheckButtonEnabled(true);
			checkGradeUI_.setChangeButtonEnabled(true);
			checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);

		}
	}

	
	
	public String checkGrade(float asg1Mark, float asg2Mark, float examMark) {
		IUnit unit = UnitManager.getInstance().getUnit(currentUnitCode_);
		String grade = unit.getGrade(asg1Mark, asg2Mark, examMark);
		return grade;
	}

	
	
	public void enableChangeMarks() {
		checkGradeUI_.setChangeButtonEnabled(false);
		checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(true);
	}

	
	
	public void saveGrade(float asg1Mark, float asg2Mark, float examMark) {
		IStudent student = StudentManager.getInstance().getStudent(currentStudentID_);

		IRecord record = student.getUnitRecord(currentUnitCode_);
		record.setAsg1Mark(asg1Mark);
		record.setAsg2Mark(asg2Mark);
		record.setExamMark(examMark);
		RecordManager.getInstance().saveRecord(record);

		checkGradeUI_.setChangeButtonEnabled(true);
		checkGradeUI_.setMarkFieldsEditableSaveButtonEnabled(false);
	}
	
}
