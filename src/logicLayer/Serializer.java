package logicLayer;

import dataLayer.DataService;

/**
 * Abstract class, from which extends all serializers in Calendar program.
 */
public abstract class Serializer
{
	/**
	 * Exports data to file.
	 * @param String fileName
	 * @param DataService data
	 */
	public void exportData(String fileName, DataService data) throws ExportException
	{}

	/**
	 * Imports data from file.
	 * @param String fileName
	 * @return DataService
	 */
	public DataService importData(String fileName) throws ImportException
	{	return null; 	}

	/**
	 * Returns Strings used for settings menu.
	 * @return String
	 */
	public String toString()
	{	return null;	}
	
	/**
	 * Getter for fileFormat.
	 * @return String
	 */
	public String getFileFormat()
	{	return null;	}
}
