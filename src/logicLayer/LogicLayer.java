package logicLayer;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;

import dataLayer.DataLayerException;
import dataLayer.DataService;
import dataLayer.DataServiceException;
import dataLayer.Event;
import dataLayer.Person;
import dataLayer.Reminder;
import dataLayer.Settings;

public class LogicLayer
{
	private DataService dataService = new DataService();
	private Settings settings = new Settings();
		
	public void setDataService(DataService dataService)
	{
		this.dataService = dataService;
	}

	public DataService getDataService()
	{
		return dataService;
	}

	/////////////////////////////////////////////////////
	// Settings
	/////////////////////////////////////////////////////
	
	public Settings getSettings()
	{
		return settings;
	}

	public String getFileName()
	{
		return settings.getFileName();
	}
	
	public Serializer getSerializer()
	{
		return settings.getSerializer();
	}
	
	public String getFileFormat()
	{
		return this.getSerializer().getFileFormat();
	}
	
	public void setFileName(String fileName)
	{
		settings.setFileName(fileName);
	}
	
	public void setSerializer(Serializer serializer )
	{
		settings.setSerializer(serializer);
	}
	
	public void setSettings(Settings settings)
	{
		this.settings = settings;
	}	
	
	public String getDefaultFileName()
	{
		return "autosave";
	}
	
	public Serializer getDefaultSerializer()
	{
		return new XMLSerializer();
	}
	
	public void setDefaultSettings()
	{
		settings.setFileName(this.getDefaultFileName());
		settings.setSerializer(this.getDefaultSerializer());
	}
	
	public String getFinalFileName()
	{
		return this.getFileName() + this.getFileFormat();
	}
	
	/////////////////////////////////////////////////////
	// Events
	/////////////////////////////////////////////////////
	
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

	public void deleteOldEvents(Calendar calendar) throws LogicLayerException
	{
		try
		{
			for (Event event : dataService.getAllEvents())
			{
				if (0 > event.getCalendar().compareTo(calendar))
					dataService.deleteEvent(event);				
			}
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
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
	
	public ArrayList<Event> getAllEvents()
	{
		return dataService.getAllEvents();
	}
	
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
	
	public DefaultListModel<Event> getAllEventsFromDateWithPlaceDLM(Calendar date)
	{
		DefaultListModel<Event> eventsList = new DefaultListModel<Event>();
		
		ArrayList<Event> list = this.getAllEventsFromDate(date);
		list.sort(null);
		
		for(Event event : list )
		{
			if (event.getPlace() != "")
				eventsList.addElement(event);
		}
		
		return eventsList;
	}
	
	public ArrayList<Person> getAllPeopleFromEvent(Event event)
	{
		return event.getPeopleList();
	}
	
	public DefaultListModel<Person> getAllPeopleFromEventDLM(Event event)
	{
		DefaultListModel<Person> list = new DefaultListModel<Person>();
		
		for(Person person : this.getAllPeopleFromEvent(event))
			list.addElement(person);
		
		return list;
	}
	
	public Reminder getReminderForEvent(Event event) 
	{
		return dataService.getReminderForEvent(event);
	}
		
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
	
	public ArrayList<Person> getAllPeople()
	{
		return dataService.getAllPeople();
	}
	
	
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
	
	public void deleteReminder(Reminder reminder)
	{
		dataService.deleteReminder(reminder);
	}
	
	public Reminder getReminder(int index)
	{
		return dataService.getReminder(index);
	}
	
	public ArrayList<Reminder> getAllReminders()
	{
		return dataService.getAllReminders();
	}
}
