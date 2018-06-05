package dataLayer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Reminder represents reminder for an Event.
 */
@SuppressWarnings("serial")
public class Reminder implements Comparable<Calendar>, Serializable
{
	private Calendar calendar = GregorianCalendar.getInstance();
	private Event event;
	
	/**
	 * Constructs a Reminder with no detail date and event.
	 */
	public Reminder()
	{	}
	
	/**
	 * Constructs a Reminder with specified date and event.
	 * @param date, which represents Reminder's calendar.
	 * @param event, which represents specified Event.
	 * @throws DataLayerException.
	 */
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
	
	/**
	 * Getter for Reminder's calendar.
	 * @return Reminder's calendar, which represents date.
	 */
	public Calendar getCalendar() 
	{
		return calendar;
	}

	/**
	 * Set a new specified Reminder's calendar.
	 * @param calendar, which represents Reminder's calendar.
	 */
	public void setDate(Calendar calendar) 
	{
		this.calendar = calendar;
	}

	/**
	 * Getter for Reminder's event.
	 * @return event, which represents Reminder's event.
	 */
	public Event getEvent() 
	{
		return event;
	}

	/**
	 * Set a new Reminder's event.
	 * @param event, which represents Reminder's event.
	 */
	public void setEvent(Event event) 
	{
		this.event = event;
	}
	
	/**
	 * Counts difference between start of reminder's event and reminder's date. 
	 * @return Amount of minutes between two dates converted to integer.
	 */
	public int getDateDiffrence()
	{
		return (int) (event.getCalendar().getTimeInMillis() - calendar.getTimeInMillis()) / 60000;
	}

	/**
	 * Concatenates Reminder's year, month, day, hour, minute, second and converts to String.
	 * @return String with Reminder's date description in smalldatetime format for MSSQL Data Base.
	 */
	public String getDatabaseDate()
	{
		String dateDescription = "";
		
		dateDescription += getCalendar().get(Calendar.YEAR) + "-";
		dateDescription += (getCalendar().get(Calendar.MONTH) + 1) + "-";		
		dateDescription += getCalendar().get(Calendar.DAY_OF_MONTH) + " ";
		dateDescription += getCalendar().get(Calendar.HOUR_OF_DAY) + ":";
		dateDescription += getCalendar().get(Calendar.MINUTE) + ":";
		dateDescription += getCalendar().get(Calendar.SECOND);
		
		return dateDescription;
	}

	/**
	 * Compares Reminder's calendar with another calendar.
	 * @return 0 if dates are equal, 1 if not.
	 */
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
	
	/**
	 * Converts to String and concatenates Reminder's hour, minute, day, month, year.
	 * @return String which describes Reminder's date description.
	 */
	public String toString()
	{		
		String dateDescription = new String();

		if (getCalendar().get(Calendar.HOUR_OF_DAY) < 10) 
			dateDescription += "0";
		dateDescription += getCalendar().get(Calendar.HOUR_OF_DAY) + ":";
		
		if (getCalendar().get(Calendar.MINUTE) < 10) 
			dateDescription += "0";
		dateDescription += getCalendar().get(Calendar.MINUTE) + "      ";
		
		dateDescription += calendar.get(Calendar.DAY_OF_MONTH) + ".";
		dateDescription += calendar.get(Calendar.MONTH) + ".";		
		dateDescription += calendar.get(Calendar.YEAR);

		return dateDescription;
	}
}
