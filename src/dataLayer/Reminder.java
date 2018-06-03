package dataLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Reminder implements Comparable<Calendar>, Serializable
{
	private Calendar calendar = GregorianCalendar.getInstance();
	private Event event;
	
	public Reminder()
	{	}
	
	public Reminder(Calendar date, Event event) throws DataLayerException
	{
		if(date == null || event == null)
			throw new DataLayerException("Error creating reminder");	
		
		else
		{			
			this.calendar = date;
			this.event = event;			
		}
	}
	
	public Calendar getCalendar() 
	{
		return calendar;
	}


	public void setDate(Calendar calendar) 
	{
		this.calendar = calendar;
	}


	public Event getEvent() 
	{
		return event;
	}

	public void setEvent(Event event) 
	{
		this.event = event;
	}

	@Override
	public int compareTo(Calendar anotherCalendar) {
		if(	calendar.get(Calendar.YEAR) == anotherCalendar.get(Calendar.YEAR)
			&& calendar.get(Calendar.MONTH) == anotherCalendar.get(Calendar.MONTH)
			&& calendar.get(Calendar.DAY_OF_MONTH) == anotherCalendar.get(Calendar.DAY_OF_MONTH)
			&& calendar.get(Calendar.HOUR_OF_DAY) == anotherCalendar.get(Calendar.HOUR_OF_DAY)
			&& calendar.get(Calendar.MINUTE) == anotherCalendar.get(Calendar.MINUTE)) 
			return 0;
		
		else
			return calendar.compareTo(anotherCalendar);
	}
	
	public String toString()
	{		
		return String.valueOf(calendar.getTime());
	}
}
