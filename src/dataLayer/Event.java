package dataLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

@SuppressWarnings("serial")
public class Event implements Comparable<Event>, Serializable
{	
	private String name;
	private Calendar date = GregorianCalendar.getInstance();
	private String description;
	private String place;
	private HashSet<Person> peopleList = new HashSet<Person>();
		
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
	
	public HashSet<Person> getPeopleList()
	{
		return peopleList;
	}

	public void setPeopleList(HashSet<Person> peopleList)
	{
		this.peopleList = peopleList;
	}
	
	public void addPerson(Person person)
	{
		peopleList.add(person);
	}
	
	public String getStringDate()
	{
		String dateDescription = "";
		
		dateDescription += date.get(Calendar.YEAR) + "-";
		dateDescription += date.get(Calendar.MONTH) + "-";		
		dateDescription += date.get(Calendar.DAY_OF_MONTH) + " ";
		dateDescription += date.get(Calendar.HOUR_OF_DAY) + ":";
		dateDescription += date.get(Calendar.MINUTE) + ":";
		dateDescription += date.get(Calendar.SECOND);
		
		return dateDescription;
	}
	
	public String toString()
	{
		String dateDescription = "";
		
		dateDescription += date.get(Calendar.DAY_OF_MONTH) + ".";
		dateDescription += date.get(Calendar.MONTH) + ".";		
		dateDescription += date.get(Calendar.YEAR) + "   ";

		dateDescription += date.get(Calendar.HOUR_OF_DAY) + ":";
		dateDescription += date.get(Calendar.MINUTE);
		
		
		return name + "\nDate: " + dateDescription + "\nPlace: " + place + "\nDescription: " + description;
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
