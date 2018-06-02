package logicLayer;

import java.io.*;


import dataLayer.DataService;

public class BinarySerializer extends Serializer
{
	public void exportData(String filename, DataService data) throws ExportException
	{
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try
		{
			fileOut = new FileOutputStream(filename);
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
	
	public String toString()
	{
		return "Binary";
	}

	public String getFileFormat()
	{
		return ".bin";
	}
}
