package dataLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * Event represents event with possibly assigned people.
 */
@SuppressWarnings("serial")
public class Event implements Comparable<Event>, Serializable
{	
	private String name;
	private Calendar calendar = GregorianCalendar.getInstance();
	private String description;
	private String place;
	private ArrayList<Person> peopleList = new ArrayList<Person>();
		
	/**
	 * Initializes a newly created Event object with specified name, calendar, description, place.
	 * @param name
	 * @param calendar
	 * @param description
	 * @param place
	 * @throws DataLayerException
	 */
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
	
	/**
	 * Initializes a newly created Event object with specified name, calendar, description, place and people.
	 * @param name
	 * @param calendar
	 * @param description
	 * @param place
	 * @param people
	 * @throws DataLayerException
	 */
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
	
	/**
	 * Getter for Event's name.
	 * @return name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets a new Event's name.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Getter for a Event's calendar.
	 * @return calendar
	 */
	public Calendar getCalendar()
	{
		return calendar;
	}

	/**
	 * Sets a new Event's calendar
	 * @param calendar.
	 */
	public void setCalendar(Calendar calendar)
	{
		this.calendar = calendar;
	}

	/**
	 * Getter for Event's description.
	 * @return description.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets a new Event's description.
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Getter for Event's place.
	 * @return place.
	 */
	public String getPlace()
	{
		return place;
	}

	/**
	 * Sets a new Event's place.
	 * @param place
	 */
	public void setPlace(String place)
	{
		this.place = place;
	}
	
	/**
	 * Getter for list of people in Event.
	 * @return peopleList.
	 */
	public ArrayList<Person> getPeopleList()
	{
		return peopleList;
	}

	/**
	 * Sets a new list of people in Event.
	 * @param peopleList
	 */
	public void setPeopleList(ArrayList<Person> peopleList)
	{
		this.peopleList = peopleList;
	}
	
	/**
	 * Adds a new  Person in peopleList.
	 * @param person
	 */
	public void addPerson(Person person)
	{
		peopleList.add(person);
	}
	
	/**
	 * Adds a new list of  Person 
	 * @param people
	 */
	public void addPeopleList(ArrayList<Person> people)
	{
		for(Person person : peopleList)
			peopleList.add(person);
	}
	
	/**
	 * Concatenates Event's year, month, day, hour, minute, second and converts to String.
	 * @return String with Event's date description in smalldatetime format for MSSQL Data Base.
	 */
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
	
	/**
	 * Getter for Event's hour. 
	 * @return hour_of_day from Event's calendar. 
	 */
	public int getHour()
	{
		return getCalendar().get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Getter for Event's minute. 
	 * @return minute from Event's calendar. 
	 */
	public int getMinute()
	{
		return getCalendar().get(Calendar.MINUTE);
	}
	
	/**
	 * Sets a new Event's hour.
	 * @param hour
	 */
	public void setHour(int hour)
	{
		calendar.set(Calendar.HOUR_OF_DAY, hour);
	}
	
	/**
	 * Sets a new Event's minute.
	 * @param minute
	 */
	public void setMinute(int minute)
	{
		calendar.set(Calendar.MINUTE, minute);
	}
	
	/**
	 * Concatenates Event's hour, minute and converts to String.
	 * @return String which describes Event's time.
	 */
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
	
	/**
	 * Concatenates results from {@link #getTime()} and {@link #getName()}.
	 */
	public String toString()
	{
		return getTime() + "  -  " + getName();
	}
	
	/**
	 * Concatenates Event's details such as date, time, place, description.
	 * @return String which describes Event's date, time, place, description.
	 */
	public String toLongString()
	{
		String dateDescription = new String();
		
		dateDescription += calendar.get(Calendar.DAY_OF_MONTH) + ".";
		dateDescription += calendar.get(Calendar.MONTH) + ".";		
		dateDescription += calendar.get(Calendar.YEAR) + "   ";

		dateDescription += getTime();
		
		return name + "\nDate: " + dateDescription + "\nPlace: " + place + "\nDescription: " + description;
	}
	
	/**
	 * Compares Event's calendar with other Event's calendar. If they're equal, then compares Events names.
	 * @return 0, if Events calendars and Events names are equal. 1, if Events calendars are equal and Events names aren't equal. 1, if Events calendars aren't equal.
	 */
	public int compareTo(Event event)
	{
		int dataComparision = calendar.compareTo(event.calendar);
		
		if(dataComparision == 0)
			return name.compareTo(event.name);
		
		else
			return dataComparision;
	}
}
