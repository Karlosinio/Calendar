package dataLayer;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DataContext implements Serializable
{
	ArrayList<Event> eventsList = new ArrayList<Event>();
	ArrayList<Person> peopleList = new ArrayList<Person>();
}
