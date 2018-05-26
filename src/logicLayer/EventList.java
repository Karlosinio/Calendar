package logicLayer;

import java.util.Collections;
import java.util.Vector;

import dataLayer.Event;
import dataLayer.NameComparator;

public class EventList
{
	
	Vector<Event> events = new Vector<Event>(10);
	
	public void addEvent(Event event)
	{	events.add(event);	}
	
	public void removeEvent(Event event)
	{	events.remove(event);	}
	
	public void printEvents()
	{
		for(int i=0; i<events.size(); i++)
			System.out.println(events.get(i) + "\n");
	}
	
	public void dataSort()
	{	Collections.sort(events);	}
	
	public void nameSort()
	{	Collections.sort(events, new NameComparator());		}

}
