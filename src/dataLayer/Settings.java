package dataLayer;

import java.io.Serializable;

import logicLayer.Serializer;

@SuppressWarnings("serial")
public class Settings implements Serializable
{
	String fileName;
	Serializer serializer;

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public Serializer getSerializer()
	{
		return serializer;
	}

	public void setSerializer(Serializer serializer)
	{
		this.serializer = serializer;
	}
	


}
