package logicLayer;

import dataLayer.DataService;

/**
 * Abstract class, from which extends all serializers in Calendar program.
 */
public abstract class Serializer
{
	/**
	 * Exports data to file.
	 * @param fileName, which is name of file.
	 * @param data, which represents DataService.
	 */
	public void exportData(String fileName, DataService data) throws ExportException
	{}

	/**
	 * Imports data from file.
	 * @param fileName, which is name of file.
	 * @return data, which represents DataService.
	 */
	public DataService importData(String fileName) throws ImportException
	{	return null; 	}

	/**
	 * Returns Strings used for settings menu.
	 * @return null.
	 */
	public String toString()
	{	return null;	}
	
	/**
	 * Getter for fileFormat.
	 * @return null.
	 */
	public String getFileFormat()
	{	return null;	}
}
