package dataLayer;

import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class DataService implements Serializable
{
	private DataContext dataContext = new DataContext();
	
	private int eventCounter = 0;	
	private int peopleCounter = 0;

	
	public void createEvent(Event event)
	{
		dataContext.eventsMap.put(eventCounter, event);
		eventCounter++;
	}

	public Event getEvent(int id) throws DataServiceException
	{
		if(dataContext.eventsMap.containsKey(id))
			return dataContext.eventsMap.get(id);

		else
			throw new DataServiceException("Event not found (wrong ID)");
	}

	public void updateEvent(int id, Event event) throws DataServiceException
	{
		if(dataContext.eventsMap.containsKey(id))
			dataContext.eventsMap.put(id, event);

		else
			throw new DataServiceException("Event not found (wrong ID)");		
	}
	
	public void deleteEvent(int id) throws DataServiceException 
	{
		if(dataContext.eventsMap.containsKey(id))
			dataContext.eventsMap.remove(id);

		else
			throw new DataServiceException("Event not found (wrong ID)");	
	}
	
	public void deleteEvent(Event event) throws DataServiceException 
	{
		if(dataContext.eventsMap.containsValue(event))
			dataContext.eventsMap.values().remove(event);

		else
			throw new DataServiceException("Event not found");
	}

	public TreeMap<Integer, Event> getAllEvents()
	{
		return dataContext.eventsMap;
	}
	
	public void addPeopleToEvent(int id, Person...peopleList) throws DataServiceException
	{
		for(Person person: peopleList)
			this.getEvent(id).addPerson(person);
	}
	
		
	public void createPerson(Person person)
	{			
		dataContext.peopleMap.put(peopleCounter, person);
		peopleCounter++;
	}

	public Person getPerson(int id) throws DataServiceException
	{
		if(dataContext.peopleMap.containsKey(id))
			return dataContext.peopleMap.get(id);

		else
			throw new DataServiceException("Person not found (wrong ID)");	
	}
	
	public void updatePerson(int id, Person person) throws DataServiceException
	{
		if(dataContext.peopleMap.containsKey(id))
			dataContext.peopleMap.put(id, person);

		else
			throw new DataServiceException("Person not found (wrong ID)");		
	}

	public void deletePerson(int id) throws DataServiceException
	{
		if(dataContext.peopleMap.containsKey(id))
			dataContext.peopleMap.remove(id);
		
		else 
			throw new DataServiceException("Person not found (wrong ID)");	
	}
	
	public void deletePerson(Person person) throws DataServiceException
	{
		if(dataContext.peopleMap.containsValue(person))
			dataContext.peopleMap.values().remove(person);

		else
			throw new DataServiceException("Person not found");	
	}
	
	public TreeMap<Integer, Person> getAllPeople()
	{
		return dataContext.peopleMap;
	}

}
