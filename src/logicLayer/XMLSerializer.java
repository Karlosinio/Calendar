package logicLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import dataLayer.DataService;

public class XMLSerializer implements Serializer
{
	public static void exportData(String fileName, DataService data) throws ExportException
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

	
	public static DataService importData(String fileName) throws ImportException
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
}
