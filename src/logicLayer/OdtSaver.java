package logicLayer;

import org.odftoolkit.simple.TextDocument;

import dataLayer.DataService;
import dataLayer.Event;

/**
 * OdtSaver saves data in Odt format.
 */
public class OdtSaver extends Serializer
{
	/**
	 * Concatenates all Events with their assigned people and converts to String.
	 * @param dataService, which represents DataService.
	 * @return String with Event and their assigned peopleList.
	 */
	private String EventsToString(DataService dataService)
	{
		String result = new String("");
		
		result += "\nEvents with people\n";
		for(Event event : dataService.getAllEvents())
		{
			result += event.toString();
			result += event.getPeopleList();
		}

		return result;
	}
	
	/**
	 * Concatenates all people and converts to String.
	 * @param dataService, which represents DataService.
	 * @return String with all people.
	 */
	private String PeopleToString(DataService dataService)
	{
		String result = new String("");
		
		result += "\nList of People\n";
		result += dataService.getAllPeople();
		
		return result;
	}
	
	/**
	 * Saves in Odt format all Events with their assigned people and all people.
	 * @param filename, which represents name of file.
	 * @param dataService, which represents DataService.
	 * @throws LogicLayerException
	 */
	@SuppressWarnings("deprecation")
	public void Save(String filename, DataService dataService) throws LogicLayerException
	{
		TextDocument outputOdt;
		
		try 
		{
			outputOdt = TextDocument.newTextDocument();
			
			outputOdt.addParagraph("Simple java app");
			outputOdt.addText(EventsToString(dataService));
			outputOdt.addText(PeopleToString(dataService));
			
			outputOdt.save(filename);
			
		} 
		
		catch(Exception e)
		{
			throw new LogicLayerException(e.getMessage());
		}
		
	}	
}
