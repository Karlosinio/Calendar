package dataLayer;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DataService implements Serializable
{
	private DataContext dataContext = new DataContext();

	/////////////////////////////////////////////////////
	// Events
	/////////////////////////////////////////////////////
	
	public void createEvent(Event event)
	{
		dataContext.eventsList.add(event);
	}

	public void updateEvent(Event initialEvent, Event finalEvent) throws DataServiceException
	{
		if(dataContext.eventsList.contains(initialEvent))
		{
			dataContext.eventsList.set(dataContext.eventsList.indexOf(initialEvent), finalEvent);
		}
		else
			throw new DataServiceException("Event not found");		
	}
	
	public void deleteEvent(Event event) throws DataServiceException 
	{
		if(dataContext.eventsList.contains(event))
			dataContext.eventsList.remove(event);

		else
			throw new DataServiceException("Event not found");
	}

	public ArrayList<Event> getAllEvents()
	{
		return dataContext.eventsList;
	}
	
	public void addPeopleToEvent(Event event, Person...peopleList) throws DataServiceException
	{
		for(Person person: peopleList)
			dataContext.eventsList.get(dataContext.eventsList.indexOf(event)).addPerson(person);
	}
	
	
	/////////////////////////////////////////////////////
	// People
	/////////////////////////////////////////////////////
	
	public void createPerson(Person person)
	{			
		dataContext.peopleList.add(person);
	}
	
	public void updatePerson(Person initialPerson, Person finalPerson) throws DataServiceException
	{
		if(dataContext.peopleList.contains(initialPerson))
			dataContext.peopleList.set(dataContext.peopleList.indexOf(initialPerson), finalPerson);

		else
			throw new DataServiceException("Person not found");		
	}

	
	public void deletePerson(Person person) throws DataServiceException
	{
		if(dataContext.peopleList.contains(person))
			dataContext.peopleList.remove(person);

		else
			throw new DataServiceException("Person not found");	
	}
	
	public ArrayList<Person> getAllPeople()
	{
		return dataContext.peopleList;
	}

}
