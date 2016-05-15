package datamanagement.daos.xml;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.JDOMException;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import datamanagement.AppProperties;
import datamanagement.daos.*;


public class DataManager implements IDataManager {
	
	private static DataManager self_ = null;
	private Document xmlDocument_;

	private static IStudentDAO studentDAO_ = null;
	private static ISubjectDAO subjectDAO_ = null;
	private static IRecordDAO  recordDAO_  = null;
	
	
	
	public static DataManager getInstance() {
		if (self_ == null) {
			self_ = new DataManager();
		}
		return self_;
	}

	
	
	private DataManager() {
		init();
	}

	
	
	public void init() {
		Properties properties = AppProperties.getInstance().getProperties();
		String xmlFile = properties.getProperty("XMLFILE");
		
		try {
			SAXBuilder builder = new SAXBuilder();
			builder.setExpandEntities(true);
			xmlDocument_ = builder.build(xmlFile);
			
			studentDAO_ = StudentDAO.getInstance();
			subjectDAO_ = SubjectDAO.getInstance();
			recordDAO_  = RecordDAO.getInstance();
		}
		catch (JDOMException e) {
			System.err.printf("%s", "DBMD: XMLManager : init : caught JDOMException\n");
			throw new RuntimeException("DBMD: XMLManager : init : JDOMException");
		} 
		catch (IOException e) {
			System.err.printf("%s", "DBMD: XMLManager : init : caught IOException\n");
			throw new RuntimeException("DBMD: XMLManager : init : IOException");
		}
	}

	
	
	public Document getDocument() {
		return xmlDocument_;
	}

	
	
	public void saveDocument() {
		Properties properties = AppProperties.getInstance().getProperties();
		String xmlFile = properties.getProperty("XMLFILE");
		
		try (FileWriter fout = new FileWriter(xmlFile)) {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(xmlDocument_, fout);
			fout.close();
		} 
		catch (IOException ioe) {
			System.err.printf("%s\n", "DBMD : XMLManager : saveDocument : Error saving XML to " + xmlFile);
			throw new RuntimeException("DBMD: XMLManager : saveDocument : error writing to file");
		}
	}
	
	
	
	public void close() {
		System.out.println("XML DataManager closing");
	}



	@Override
	public IStudentDAO getStudentDAO() {
		return studentDAO_;
	}



	@Override
	public ISubjectDAO getSubjectDAO() {
		return subjectDAO_;
	}



	@Override
	public IRecordDAO getRecordDAO() {
		return recordDAO_;
	}
	
}
