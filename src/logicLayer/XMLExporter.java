package logicLayer;

import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import dataLayer.DataService;

public class XMLExporter implements Exporter
{
	@Override
	public void save(String fileName, DataService data) throws ExportException
	{
		XStream xstream = new XStream(new StaxDriver());
		
		try
		{
			xstream.toXML(data, new FileWriter(fileName));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
