package logicLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import dataLayer.Settings;


public class XMLSettingsSerializer
{
	public static void exportData(Settings settings) throws ExportException
	{
		XStream xstream = new XStream(new StaxDriver());
		
		try
		{
			xstream.toXML(settings, new FileWriter("settings.xml"));
		}
		catch (IOException e)
		{
			throw new ExportException("Settings file not found");
		}
	}

	
	public static Settings importData() throws ImportException
	{
		FileInputStream fileInput = null;
		XStream xstream = new XStream(new StaxDriver());
		
		try
		{
			fileInput = new FileInputStream("settings.xml");
		}
		catch (FileNotFoundException e)
		{
			throw new ImportException("Settings file not found");
		}
		
		Settings ss = (Settings)xstream.fromXML(fileInput);
		
		return ss;
	}
}
