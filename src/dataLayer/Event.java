package dataLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Event implements Comparable<Event>, Serializable
{	
	private String name;
	private Calendar calendar = GregorianCalendar.getInstance();
	private String description;
	private String place;
	private ArrayList<Person> peopleList = new ArrayList<Person>();
		
	public Event(String name, Calendar calendar, String description, String place) throws DataLayerException
	{
		if (name.isEmpty())
			throw new DataLayerException("Name cannot be empty");
			
		else
		{			
			this.name = name;
			this.calendar = calendar;
			this.description = description;
			this.place = place;
		}
	}
	
	public Event(String name, Calendar calendar, String description, String place, ArrayList<Person> people) throws DataLayerException
	{
		if (name.isEmpty())
			throw new DataLayerException("Name cannot be empty");
			
		else
		{			
			this.name = name;
			this.calendar = calendar;
			this.description = description;
			this.place = place;
			
			for(Person person: people)
			peopleList.add(person);
		}
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Calendar getCalendar()
	{
		return calendar;
	}

	public void setCalendar(Calendar calendar)
	{
		this.calendar = calendar;
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
	
	public ArrayList<Person> getPeopleList()
	{
		return peopleList;
	}

	public void setPeopleList(ArrayList<Person> peopleList)
	{
		this.peopleList = peopleList;
	}
	
	public void addPerson(Person person)
	{
		peopleList.add(person);
	}
	
	public void addPeopleList(ArrayList<Person> people)
	{
		for(Person person : peopleList)
			peopleList.add(person);
	}
	
	public String getDatabaseDate()
	{
		String dateDescription = "";
		
		dateDescription += calendar.get(Calendar.YEAR) + "-";
		dateDescription += (calendar.get(Calendar.MONTH) + 1) + "-";		
		dateDescription += calendar.get(Calendar.DAY_OF_MONTH) + " ";
		dateDescription += calendar.get(Calendar.HOUR_OF_DAY) + ":";
		dateDescription += calendar.get(Calendar.MINUTE) + ":";
		dateDescription += calendar.get(Calendar.SECOND);
		
		return dateDescription;
	}
	
	public int getHour()
	{
		return getCalendar().get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute()
	{
		return getCalendar().get(Calendar.MINUTE);
	}
	
	public void setHour(int hour)
	{
		calendar.set(Calendar.HOUR_OF_DAY, hour);
	}
	
	public void setMinute(int minute)
	{
		calendar.set(Calendar.MINUTE, minute);
	}
	
	public String getTime()
	{
		String time = new String();

		if (getCalendar().get(Calendar.HOUR_OF_DAY) < 10) 
			time += "0";
		time += getCalendar().get(Calendar.HOUR_OF_DAY) + ":";
		
		if (getCalendar().get(Calendar.MINUTE) < 10) 
			time += "0";
		time += getCalendar().get(Calendar.MINUTE);
		
		return time;
	}
	
	public String toString()
	{
		return getTime() + "  -  " + getName();
	}
	
	public String toLongString()
	{
		String dateDescription = new String();
		
		dateDescription += calendar.get(Calendar.DAY_OF_MONTH) + ".";
		dateDescription += calendar.get(Calendar.MONTH) + ".";		
		dateDescription += calendar.get(Calendar.YEAR) + "   ";

		dateDescription += getTime();
		
		return name + "\nDate: " + dateDescription + "\nPlace: " + place + "\nDescription: " + description;
	}
	
	
	public int compareTo(Event event)
	{
		int dataComparision = calendar.compareTo(event.calendar);
		
		if(dataComparision == 0)
			return name.compareTo(event.name);
		
		else
			return dataComparision;
	}
}
