package logicLayer;

import java.io.*;


import dataLayer.DataService;

/**
 * BinarySerializer is responsible for serializing data to binary format.
 */
public class BinarySerializer extends Serializer
{
	/**
	 * Exports data from DataService and creates new binary file.
	 * @param String fileName
	 * @param DataService data
	 */
	public void exportData(String fileName, DataService data) throws ExportException
	{
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try
		{
			fileOut = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
		}
		catch (FileNotFoundException e)
		{
			throw new ExportException("Unable to create file");
		}
		catch (IOException e)
		{
			throw new ExportException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new ExportException("Unexpected exception" + e.getMessage());
		}
		finally
		{
			try
			{
				out.close();
				fileOut.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				throw new ExportException("Unexpected exception" + e.getMessage());
			}

		}

	}
	
	/**
	 * Imports data from binary file.
	 * @param String fileName
	 * @return DataService
	 */
	public DataService importData(String fileName) throws ImportException
	{
		DataService data = null;
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		
		try
		{
			fileIn = new FileInputStream(fileName);
			in = new ObjectInputStream(fileIn);
			data = (DataService) in.readObject();
		}
		catch (IOException i)
		{
			throw new ImportException("File not found");
			
		} catch (ClassNotFoundException c)
		{
			throw new ImportException("DataService class not found");
			//c.printStackTrace();
		}
		finally
		{
			if(fileIn!=null)
			{
				try
				{
					fileIn.close();
				}
				catch (IOException e)
				{	
					e.printStackTrace();
				}
			}
				
			if(in!=null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	/**
	 * Return String used for settings menu.
	 * @return String "Binary".
	 */
	public String toString()
	{
		return "Binary";
	}

	/**
	 * Getter for fileFormat.
	 * @return String ".bin".
	 */
	public String getFileFormat()
	{
		return ".bin";
	}
}
