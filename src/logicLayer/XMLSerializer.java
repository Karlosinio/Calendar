package logicLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import dataLayer.DataService;

/**
 * XMLSerializer is responsible for XML serialization.
 */
public class XMLSerializer extends Serializer
{
	/**
	 * Exports data to XML.
	 * @param String fileName
	 * @param DataService data
	 */
	public void exportData(String fileName, DataService data) throws ExportException
	{
		XStream xstream = new XStream(new StaxDriver());
		
		try
		{
			xstream.toXML(data, new FileWriter(fileName));
		}
		catch (IOException e)
		{
			throw new ExportException("File not found");
		}
	}

	/**
	 * Imports data from XML.
	 * @param String fileName
	 * @return DataService
	 */
	public DataService importData(String fileName) throws ImportException
	{
		FileInputStream fileInput = null;
		XStream xstream = new XStream(new StaxDriver());
		
		try
		{
			fileInput = new FileInputStream(fileName);
		}
		catch (FileNotFoundException e)
		{
			throw new ImportException("File not found");
		}
		
		DataService data = (DataService)xstream.fromXML(fileInput);
		
		return data;
	}
	
	/**
	 * Returns Strings used for settings menu.
	 * @return String "XML".
	 */
	public String toString()
	{
		return "XML";
	}
	
	/**
	 * Getter for fileFormat.
	 * @return String ".xml".
	 */
	public String getFileFormat()
	{
		return ".xml";
	}
}
