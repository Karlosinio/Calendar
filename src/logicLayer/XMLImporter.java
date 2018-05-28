package logicLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import dataLayer.DataService;

public class XMLImporter implements Importer
{

	@Override
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

}
