package dataLayer;

import java.io.Serializable;

import logicLayer.Serializer;

/**
 * 
 * 
 *
 */
@SuppressWarnings("serial")
public class Settings implements Serializable
{
	String fileName;
	Serializer serializer;

	/**
	 * Getter for a Setting's fileName.
	 * @return fileName.
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Set a new fileName.
	 * @param fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Getter for a Setting's serializer.
	 * @return serializer.
	 */
	public Serializer getSerializer()
	{
		return serializer;
	}

	/**
	 * Set a new Setting's serializer.
	 * @param serializer
	 */
	public void setSerializer(Serializer serializer)
	{
		this.serializer = serializer;
	}
	


}
