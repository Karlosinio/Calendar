package dataLayer;

import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class DataContext implements Serializable
{
	TreeMap<Integer, Event> eventsMap = new TreeMap<Integer, Event>();
	TreeMap<Integer, Person> peopleMap = new TreeMap<Integer, Person>();
}
