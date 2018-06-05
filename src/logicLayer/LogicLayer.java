package logicLayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.DefaultListModel;

import dataLayer.DataLayerException;
import dataLayer.DataService;
import dataLayer.DataServiceException;
import dataLayer.Event;
import dataLayer.Person;
import dataLayer.Reminder;
import dataLayer.Settings;

/**
 * Class that implements all important methods for aplplication's logic layer - this class operates on program data (events, people, reminders) and settings
 */
public class LogicLayer
{
	private DataService dataService = new DataService();
	private Settings settings = new Settings();
		
	/**
	 * Sets a dataService.
	 * @param dataService, which a LogicLayer's DataService.
	 */
	public void setDataService(DataService dataService)
	{
		this.dataService = dataService;
	}

	/**
	 * Getter for a dataService.
	 * @return LogicLayer's dataService.
	 */
	public DataService getDataService()
	{
		return dataService;
	}

	/////////////////////////////////////////////////////
	// Settings
	/////////////////////////////////////////////////////
	
	/**
	 * Getter for a settings.
	 * @return LogicLayer's settings.
	 */
	public Settings getSettings()
	{
		return settings;
	}

	/**
	 * Getter for a fileName.
	 * @return LogicLayer's settings fileName.
	 */
	public String getFileName()
	{
		return settings.getFileName();
	}
	
	/**
	 * Getter for a serializer.
	 * @return LogicLayer's settings serializer.
	 */
	public Serializer getSerializer()
	{
		return settings.getSerializer();
	}
	
	/**
	 * Getter for a serializer's fileFormat.
	 * @return settings serializer's fileFormat.
	 */
	public String getFileFormat()
	{
		return this.getSerializer().getFileFormat();
	}
	
	/**
	 * Sets a new settings fileName.
	 * @param fileName, which represents settings fileName.
	 */
	public void setFileName(String fileName)
	{
		settings.setFileName(fileName);
	}
	
	/**
	 * Sets a new settings serializer.
	 * @param serializer, which represents settings serializer.
	 */
	public void setSerializer(Serializer serializer )
	{
		settings.setSerializer(serializer);
	}
	
	/**
	 * Sets a new settings.
	 * @param settings, which represents settings serializer.
	 */
	public void setSettings(Settings settings)
	{
		this.settings = settings;
	}	
	
	/**
	 * Getter for a default fileName.
	 * @return String "autosave".
	 */
	public String getDefaultFileName()
	{
		return "autosave";
	}
	
	/**
	 * Getter for a default serializer.
	 * @return  XMLSerializer, which right now is a default serializer.
	 */
	public Serializer getDefaultSerializer()
	{
		return new XMLSerializer();
	}
	
	/**
	 * Sets a default settings fileName ({@link #getDefaultFileName()} and serializer ({@link #getDefaultSerializer()}.
	 */
	public void setDefaultSettings()
	{
		settings.setFileName(this.getDefaultFileName());
		settings.setSerializer(this.getDefaultSerializer());
	}
	
	/**
	 * Getter for a final fileName with format.
	 * @return String which represents fileName with fileFormat.
	 */
	public String getFinalFileName()
	{
		return this.getFileName() + this.getFileFormat();
	}
	
	/////////////////////////////////////////////////////
	// Events
	/////////////////////////////////////////////////////
	
	/**
	 * Creates  Event by setting new name, date, description, place.
	 * @param name, which represents Event's name.
	 * @param date, which represents Event's date.
	 * @param description, which represents Event's description.
	 * @param place, which represents Event's place.
	 * @throws LogicLayerException
	 */
	public void createEvent(String name, Calendar date, String description, String place) throws LogicLayerException
	{
		try
		{
			dataService.createEvent(new Event (name, date, description, place));
		}
		catch (DataLayerException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
		
	}
	
	/**
	 * Creates  Event by setting new name, date, description, place and ArrayList of people.
	 * @param name, which represents Event's name.
	 * @param date, which represents Event's date.
	 * @param description, which represents Event's description.
	 * @param place, which represents Event's place.
	 * @param people, which represents Event's list of assigned people.
	 * @throws LogicLayerException
	 */
	public void createEvent(String name, Calendar date, String description, String place, ArrayList<Person> people) throws LogicLayerException
	{
		try
		{
			dataService.createEvent(new Event (name, date, description, place, people));
		}
		catch (DataLayerException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
		
	}

	/**
	 * Updates Event by setting a new name, date, description, place.
	 * @param event, which represents Event.
	 * @param name, which represents Event's name.
	 * @param calendar, which represents Event's calendar.
	 * @param description, which represents Event's description.
	 * @param place, which represents Event's place.
	 * @throws LogicLayerException
	 */
	public void updateEvent(Event event, String name, Calendar calendar, String description, String place) throws LogicLayerException
	{
		try
		{
			dataService.updateEvent(event, name, calendar, description, place, null);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}		
	}
	
	/**
	 * Updates Event by setting a new name, date, description, place and ArrayList of people.
	 * @param event, which represents Event.
	 * @param name, which represents Event's name.
	 * @param calendar, which represents Event's calendar.
	 * @param description, which represents Event's description.
	 * @param place, which represents Event's place.
	 * @param people, which represents Event's list of assigned people.
	 * @throws LogicLayerException
	 */
	public void updateEvent(Event event, String name, Calendar calendar, String description, String place, ArrayList<Person> people) throws LogicLayerException
	{
		try
		{
			dataService.updateEvent(event, name, calendar, description, place, people);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}		
	}

	/**
	 * Deletes an Event.
	 * @param event, which represents specified Event.
	 * @throws LogicLayerException
	 */
	public void deleteEvent(Event event) throws LogicLayerException
	{
		try
		{
			dataService.deleteEvent(event);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}

	/**
	 * Deletes an Event with specified calendar.
	 * @param calendar, which specified Event's calendar.
	 * @throws LogicLayerException
	 */
	public void deleteOldEvents(Calendar calendar) throws LogicLayerException
	{
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		
		for (Event event : this.getAllEvents())
		{
			if (0 > event.getCalendar().compareTo(calendar))
				toRemove.add(this.getAllEvents().indexOf(event));
		}
		
		Collections.reverse(toRemove);
		
		for (int index : toRemove)
			this.deleteEvent(this.getAllEvents().get(index));
	}
	
	/**
	 * Adds Person to Event.
	 * @param event, which represents specified Event.
	 * @param people, which represents specified Person. 
	 * @throws LogicLayerException
	 */
	public void addPeopleToEvent(Event event, Person...people) throws LogicLayerException
	{
		try
		{
			dataService.addPeopleToEvent(event, people);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	/**
	 * Getter for an all Events. 
	 * @return ArrayList of all Events.
	 */
	public ArrayList<Event> getAllEvents()
	{
		return dataService.getAllEvents();
	}
	
	/**
	 * Getter for an all Events with specified calendar.
	 * @param date, which represents Event's calendar.
	 * @return ArrayList of all Events with specified calendar.
	 */
	public ArrayList<Event> getAllEventsFromDate(Calendar date)
	{
		ArrayList<Event> eventsList = new ArrayList<Event>();
		
		for(Event event : this.getAllEvents() )
		{			
			if (event.getCalendar().get(Calendar.YEAR) == date.get(Calendar.YEAR) && 
				event.getCalendar().get(Calendar.MONTH)	== date.get(Calendar.MONTH) && 
				event.getCalendar().get(Calendar.DATE) == date.get(Calendar.DATE))
			{
				eventsList.add(event);
			}
		}
	
		return eventsList;
	}

	/**
	 * Getter for an all Events with specified calendar.
	 * @param date, which represents Event's calendar.
	 * @return  DefaultListModel of all Events with specified calendar.
	 */
	public DefaultListModel<Event> getAllEventsFromDateDLM(Calendar date)
	{
		DefaultListModel<Event> eventsList = new DefaultListModel<Event>();
		
		ArrayList<Event> list = this.getAllEventsFromDate(date);
		list.sort(null);
		
		for(Event event : list )
		{			
			eventsList.addElement(event);
		}
		
		return eventsList;
	}
	
	/**
	 * Getter for all Events with specified calendar and place.
	 * @param date, which represents Event's calendar.
	 * @return DefaultListModel of Event with specified calendar and place.
	 */
	public DefaultListModel<Event> getAllEventsFromDateWithPlaceDLM(Calendar date)
	{
		DefaultListModel<Event> eventsList = new DefaultListModel<Event>();
			
		ArrayList<Event> list = this.getAllEventsFromDate(date);
		list.sort(null);
		
		for(Event event : list )
		{
			if (! event.getPlace().isEmpty())
				eventsList.addElement(event);
		}
		
		return eventsList;
	}
	
	/**
	 * Getter for all  Person from specified Event.
	 * @param event, which represents specified Event.
	 * @return ArrayList of people from specified Event.
	 */
	public ArrayList<Person> getAllPeopleFromEvent(Event event)
	{
		return event.getPeopleList();
	}
	
	/**
	 * Getter for all people from specified  Event.
	 * @param event, which represents specified Event.
	 * @return DefaultModelList of people from specified  Event.
	 */
	public DefaultListModel<Person> getAllPeopleFromEventDLM(Event event)
	{
		DefaultListModel<Person> list = new DefaultListModel<Person>();
		
		for(Person person : this.getAllPeopleFromEvent(event))
			list.addElement(person);
		
		return list;
	}
	
	/**
	 * Getter for a Reminder with specified  Event.
	 * @param event, which represents specified Event.
	 * @return Reminder for specified  Event.
	 */
	public Reminder getReminderForEvent(Event event) 
	{
		return dataService.getReminderForEvent(event);
	}
		
	/**
	 * Checks if a specified  Event has a  Reminder.
	 * @param event, which represents specified Event.
	 * @return true, if Event has a Reminder, false, if not.
	 */
	public boolean reminderExists(Event event) 
	{
	    for (Reminder reminder : this.getAllReminders())
	    {
	      if (reminder.getEvent().equals(event))
	        return true;
	    }
	 
	    return false; 
	}
	 
	
	/////////////////////////////////////////////////////
	// People
	/////////////////////////////////////////////////////
	
	/**
	 * Creates a new Person with specified firstName and lastName.
	 * @param firstName, which represents Person's firstName.
	 * @param lastName, which represents Person's lastName.
	 * @throws LogicLayerException
	 */
	public void createPerson(String firstName, String lastName) throws LogicLayerException
	{
		try
		{
			dataService.createPerson(new Person(firstName, lastName));
		}
		catch (DataLayerException e)
		{
			throw new LogicLayerException(e.getMessage());
		}					
	}
	
	/**
	 * Updates Person by setting a new firstName and lastName.
	 * @param person, which represents Person.
	 * @param firstName, which represents Person's firstName.
	 * @param lastName, which represents Person's lastName.
	 * @throws LogicLayerException
	 */
	public void updatePerson(Person person, String firstName, String lastName) throws LogicLayerException
	{
		try
		{
			dataService.updatePerson(person, firstName, lastName);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}		
	}
	
	/**
	 * Deletes a Person.
	 * @param person, which represents specified Person.
	 * @throws LogicLayerException
	 */
	public void deletePerson(Person person) throws LogicLayerException
	{
		try
		{
			dataService.deletePerson(person);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	/**
	 * Getter for all people.
	 * @return ArrayList with all  Person.
	 */
	public ArrayList<Person> getAllPeople()
	{
		return dataService.getAllPeople();
	}
	
	/**
	 * Getter for all people.
	 * @return DefaultListModel with all  Person.
	 */
	public DefaultListModel<Person> getAllPeopleDLM()
	{
		DefaultListModel<Person> list = new DefaultListModel<Person>();
		
		for(Person person : this.getAllPeople())
			list.addElement(person);
		
		return list;
	}
	
	/////////////////////////////////////////////////////
	// Reminders
	/////////////////////////////////////////////////////
	
	/**
	 * Creates a new Reminder with specified calendar and  Event.
	 * @param calendar, which represents Reminder's calendar.
	 * @param event, which represents Reminder's event.
	 * @throws LogicLayerException
	 */
	public void createReminder(Calendar calendar, Event event) throws LogicLayerException
	{
		try
		{
			dataService.createReminder(new Reminder(calendar, event));
		}
		catch (DataLayerException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	/**
	 * Deletes a specified Reminder.
	 * @param reminder, which represents specified Reminder.
	 */
	public void deleteReminder(Reminder reminder)
	{
		dataService.deleteReminder(reminder);
	}
	
	/**
	 * Getter for Reminder.
	 * @param index, which is index in remindersList in DataContext.
	 * @return Reminder with specified index.
	 */
	public Reminder getReminder(int index)
	{
		return dataService.getReminder(index);
	}
	
	/**
	 * Getter for all Reminders.
	 * @return ArrayList with all @Reminder.
	 */
	public ArrayList<Reminder> getAllReminders()
	{
		return dataService.getAllReminders();
	}
}
