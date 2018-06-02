package logicLayer;

import dataLayer.DataService;

public abstract class Serializer
{
	public void exportData(String fileName, DataService data) throws ExportException
	{}

	public DataService importData(String fileName) throws ImportException
	{	return null; 	}
	
	public String toString()
	{	return null;	}
	
	public String getFileFormat()
	{	return null;	}
}
