package logicLayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;

import javax.swing.DefaultListModel;

import dataLayer.DataLayerException;
import dataLayer.DataService;
import dataLayer.DataServiceException;
import dataLayer.Event;
import dataLayer.Person;

public class LogicLayer
{
	DataService data = new DataService();
	
	/////////////////////////////////////////////////////
	// Events
	/////////////////////////////////////////////////////
	
	public void createEvent(String name, Calendar date, String description, String place) throws LogicLayerException
	{
		try
		{
			data.createEvent(new Event (name, date, description, place));
		}
		catch (DataLayerException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
		
	}
	
	public Event getEvent(int id) throws LogicLayerException
	{
		try
		{
			return data.getEvent(id);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	public void updateEvent(int id, Event event) throws LogicLayerException
	{
		try
		{
			data.updateEvent(id, event);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}		
	}
	
	public void deleteEvent(int id) throws LogicLayerException
	{
		try
		{
			data.deleteEvent(id);
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
			data.deleteEvent(event);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}

	public void addPeopleToEvent(int id, Person...people) throws LogicLayerException
	{
		try
		{
			data.addPeopleToEvent(id, people);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	public TreeMap<Integer, Event> getAllEvents()
	{
		return data.getAllEvents();
	}
	
	public ArrayList<Event> getAllEventsFromDate(Calendar date)
	{
		ArrayList<Event> eventsList = new ArrayList<Event>();
		
		for(Event event : this.getAllEvents().values() )
		{			
			if (event.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) && 
				event.getDate().get(Calendar.MONTH)	== date.get(Calendar.MONTH) && 
				event.getDate().get(Calendar.DATE) == date.get(Calendar.DATE))
			{
				eventsList.add(event);
			}
		}
	
		return eventsList;
	}
	
	/////////////////////////////////////////////////////
	// People
	/////////////////////////////////////////////////////
	
	public void createPerson(String firstName, String lastName) throws LogicLayerException
	{
		try
		{
			data.createPerson(new Person(firstName, lastName));
		}
		catch (DataLayerException e)
		{
			throw new LogicLayerException(e.getMessage());
		}					
	}
	
	public Person getPerson(int id) throws LogicLayerException
	{
		try
		{
			return data.getPerson(id);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	public void updatePerson(int id, Person person) throws LogicLayerException
	{
		try
		{
			data.updatePerson(id, person);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}		
	}
	
	public void deletePerson(int id) throws LogicLayerException
	{
		try
		{
			data.deletePerson(id);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	public void deletePerson(Person p) throws LogicLayerException
	{
		try
		{
			data.deletePerson(p);
		}
		catch (DataServiceException e)
		{
			throw new LogicLayerException(e.getMessage());
		}
	}
	
	public TreeMap<Integer, Person> getAllPeople()
	{
		return data.getAllPeople();
	}
	
	
	public DefaultListModel getAllPeopleDLM()
	{
		DefaultListModel list = new DefaultListModel();
		
		for(Person person : this.getAllPeople().values() )
			list.addElement(person.toString());
		
		return list;
	}
	
	
	/////////////////////////////////////////////////////
	// Exporters
	/////////////////////////////////////////////////////
	
	private void exportData(String fileName, Exporter exporter) throws ExportException
	{
		exporter.save(fileName, data);
	}
	
	public void exportToXML(String fileName) throws ExportException
	{
		this.exportData(fileName, new XMLExporter());
	}

	/////////////////////////////////////////////////////
	// Importers
	/////////////////////////////////////////////////////
	
	private void importData(String fileName, Importer importer) throws ImportException
	{
		data = importer.importData(fileName);
	}

	public void importFromXML(String fileName) throws ImportException
	{
		this.importData(fileName, new XMLImporter());
	}

}
