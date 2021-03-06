package dataLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *DataService implements CRUD methods for Event, Person and Reminder using data from  DataContext.
 */
@SuppressWarnings("serial")
public class DataService implements Serializable
{
	private DataContext dataContext = new DataContext();

	/////////////////////////////////////////////////////
	// Events
	/////////////////////////////////////////////////////
	
	
	/**
	 * Creates a new  Event and adds It to eventsList in  DataContext.
	 * @param event represents Event.
	 */
	public void createEvent(Event event)
	{
		dataContext.eventsList.add(event);
	}

	/**
	 * Updates an Event by setting a new name, calendar, description, place and list of people.
	 * @param event represents specified Event.
	 * @param name represent Event's name.
	 * @param calendar represent Event's calendar.
	 * @param description represent Event's description.
	 * @param place represent Event's place.
	 * @param people represent Event's assigned list of people.
	 * @throws DataServiceException if event doesn't exist.
	 */
	public void updateEvent(Event event, String name, Calendar calendar, String description, String place, ArrayList<Person> people) throws DataServiceException
	{
		if(dataContext.eventsList.contains(event))
		{
			event.setName(name);
			event.setCalendar(calendar);
			event.setDescription(description);
			event.setPlace(place);
			event.setPeopleList(people);
		}
		else
			throw new DataServiceException("Event not found");		
	}
	
	/**
	 * Deletes an Event from DataContext.
	 * @param event represents Event.
	 * @throws DataServiceException if event doesn't exist.
	 */
	public void deleteEvent(Event event) throws DataServiceException 
	{
		if(dataContext.eventsList.contains(event))
			dataContext.eventsList.remove(event);

		else
			throw new DataServiceException("Event not found");
	}

	/**
	 * Getter for all Events.
	 * @return ArrayList with all Events from  DataContext.
	 */
	public ArrayList<Event> getAllEvents()
	{
		return dataContext.eventsList;
	}
	
	/**
	 * Adds people to an Event's peopleList.
	 * @param event represents Event.
	 * @param peopleList represents Event's list of assigned people.
	 * @throws DataServiceException if event doesn't exist.
	 */
	public void addPeopleToEvent(Event event, Person...peopleList) throws DataServiceException
	{
		for(Person person: peopleList)
			dataContext.eventsList.get(dataContext.eventsList.indexOf(event)).addPerson(person);
	}
	
	/**
	 * Getter for a Reminder for an Event.
	 * @param event represents Event.
	 * @return If exists, Reminder for particular Event. If not, null.
	 */
	public Reminder getReminderForEvent(Event event)
	{		
		for(Reminder r : dataContext.remindersList)
		{
			if(r.getEvent().equals(event))
				return r;
		}
		
		return null;
	}
	
	/////////////////////////////////////////////////////
	// People
	/////////////////////////////////////////////////////
	
	/**
	 * Creates a new Person and adds It to peopleList in DataContext.
	 * @param person represents Person.
	 */
	public void createPerson(Person person)
	{			
		dataContext.peopleList.add(person);
	}
	
	/**
	 * Updates a Person by setting new firstName, lastName.
	 * @param person, which represents Person.
	 * @param firstName, which represents Person's firstName.
	 * @param lastName, which represents Person's lastName.
	 * @throws DataServiceException if person doesn't exist.
	 */
	public void updatePerson(Person person, String firstName, String lastName) throws DataServiceException
	{
		if(dataContext.peopleList.contains(person))
		{
			person.setFirstName(firstName);
			person.setLastName(lastName);
		}

		else
			throw new DataServiceException("Person not found");		
	}

	/**
	 * Deletes a Person from DataContext.
	 * @param person represents Person.
	 * @throws DataServiceException if person doesn't exist.
	 */
	public void deletePerson(Person person) throws DataServiceException
	{
		if(dataContext.peopleList.contains(person))
			dataContext.peopleList.remove(person);

		else
			throw new DataServiceException("Person not found");	
	}
	
	/**
	 * Getter for all people.
	 * @return ArrayList with all people from DataContext.
	 */
	public ArrayList<Person> getAllPeople()
	{
		return dataContext.peopleList;
	}
	
	/////////////////////////////////////////////////////
	// Reminders
	/////////////////////////////////////////////////////
	
	/**
	 * Creates a new Reminder and adds It to remindersList in DataContext.
	 * @param reminder represents Reminder.
	 */
	public void createReminder(Reminder reminder)
	{
		dataContext.remindersList.add(reminder);
	}
	
	/**
	 * Deletes a Reminder from DataContext.
	 * @param reminder represents Reminder.
	 */
	public void deleteReminder(Reminder reminder)
	{
		dataContext.remindersList.remove(reminder);
	}
	
	/**
	 * Getter for a Reminder.
	 * @param index represents Reminder's index in reminderList in DataContext.
	 * @return A Reminder with specified index.
	 */
	public Reminder getReminder(int index)
	{
		return dataContext.remindersList.get(index);
	}
	
	/**
	 * Getter for all Reminders.
	 * @return ArrayList with all Reminders from DataContext.
	 */
	public ArrayList<Reminder> getAllReminders()
	{
		return dataContext.remindersList;
	}

}
