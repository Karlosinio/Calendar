package logicLayer;

import dataLayer.DataService;

public interface Serializer
{
	public static void exportData(String fileName, DataService data) throws ExportException
	{}

	public static DataService importData(String fileName) throws ImportException
	{	return null; }
}
