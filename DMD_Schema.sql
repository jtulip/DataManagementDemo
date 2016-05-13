-- /* Create Students table */
CREATE TABLE Students
	( 
		StudentId integer NOT NULL PRIMARY KEY,
		FirstName varchar(32) NOT NULL,
		LastName  varchar(32) NOT NULL
	);
	
	
-- /* Create Subjects table */
CREATE TABLE Subjects
	(
		SubjectCode character(6) NOT NULL PRIMARY KEY,
		SubjectName varchar(64) NOT NULL,	
		PScutoff  integer,
		CRcutoff  integer,
		DIcutoff  integer,
		HDcutoff  integer,
		AEcutoff  integer		
	);
	

-- /* Create Records table */
CREATE TABLE Records
	(
		SubjectCode character(6) NOT NULL,
		StudentId integer NOT NULL,
		Asg1Mark integer,
		Asg2Mark integer,
		ExamMark integer
	);

ALTER TABLE Records
	ADD CONSTRAINT pk_Records PRIMARY KEY (SubjectCode, StudentId);

ALTER TABLE Records
	ADD CONSTRAINT Record_Subjects_fk FOREIGN KEY (SubjectCode) 
		REFERENCES Subjects (SubjectCode);

ALTER TABLE Records
	ADD CONSTRAINT Record_Students_fk FOREIGN KEY (StudentId) 
		REFERENCES Students (StudentId)
		ON DELETE CASCADE;
	

INSERT INTO Students VALUES 
	(1,'Jim', 'Brown'), 
	(2, 'Jack', 'Black'), 
	(3, 'Mary', 'Contrary'),
	(4, 'Fred', 'Nurke'),
	(5, 'Edward', 'Seagoon');
	
INSERT INTO Subjects VALUES 
	('ITC203','Systems Analysis and Design', 50, 65, 75, 85, 45), 
	('ITC205','Professional Programming Practice', 50, 65, 75, 85, 45),
	('ITC303','Software Development Project 1', 50, 65, 75, 85, 45);
	
INSERT INTO Records VALUES 
	('ITC203', 1, 25, 25, 30), 
	('ITC203', 2, 15, 15, 20), 
	('ITC203', 3, 20, 20, 25),
	('ITC203', 4, 25, 25, 40 ),
	('ITC205', 2, 15, 15, 20),
	('ITC205', 3, 20, 25, 20),
	('ITC205', 5, 25, 25, 35),
	('ITC303', 1, 10, 10, 20),
	('ITC303', 3, 15, 10, 20),
	('ITC303', 4, 25, 25, 50);

	
	
