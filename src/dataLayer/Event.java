package dataLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event implements Comparable<Event>, Serializable
{
	private static final long serialVersionUID = -8248849750412520106L;
	
	private String name;
	private Calendar date = GregorianCalendar.getInstance();
	private String description;
	private String place;
		
	public Event(String name, Calendar date, String description, String place)
	{
		this.name = name;
		this.date = date;
		this.description = description;
		this.place = place;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Calendar getDate()
	{
		return date;
	}

	public void setDate(Calendar date)
	{
		this.date = date;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}
	
	public String toString()
	{
		String dataDescription = "";
		
		dataDescription += date.get(Calendar.DAY_OF_MONTH) + ".";
		dataDescription += date.get(Calendar.MONTH) + ".";		
		dataDescription += date.get(Calendar.YEAR) + "   ";

		dataDescription += date.get(Calendar.HOUR_OF_DAY) + ":";
		dataDescription += date.get(Calendar.MINUTE);
		
		
		return name + "\nDate: " + dataDescription + "\nPlace: " + place + "\nDescription: " + description;
	}
	
	
	
	public int compareTo(Event event)
	{
		int dataComparision = date.compareTo(event.date);
		
		if(dataComparision == 0)
			return name.compareTo(event.name);
		
		else
			return dataComparision;
	}
}
