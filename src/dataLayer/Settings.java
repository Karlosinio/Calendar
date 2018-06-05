package dataLayer;

import java.io.Serializable;

import logicLayer.Serializer;

/**
 * Settings is used to store information about serializer type and file name to load events, people and reminders.
 */
@SuppressWarnings("serial")
public class Settings implements Serializable
{
	private String fileName;
	private Serializer serializer;

	/**
	 * Getter for a Settings fileName.
	 * @return Settings fileName.
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Set a new fileName.
	 * @param Settings fileName.
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Getter for a Settings serializer.
	 * @return Settings serializer.
	 */
	public Serializer getSerializer()
	{
		return serializer;
	}

	/**
	 * Set a new Settings serializer.
	 * @param Settings serializer
	 */
	public void setSerializer(Serializer serializer)
	{
		this.serializer = serializer;
	}
	


}
