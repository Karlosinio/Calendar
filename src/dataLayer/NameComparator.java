package dataLayer;

import java.util.Comparator;

/**
 * NameComparator represents comparator, which compares two Events names.
 */
public class NameComparator implements Comparator<Event>
{
/**
 * Compares two Events names. 
 * @param Event event which represents one of the events.
 * @param Event anotherEvent which represents one of the events.
 */
	public int compare(Event ev1, Event ev2)
	{
		int nameComparision = ev1.getName().compareTo(ev2.getName());
		
		if(nameComparision == 0)
			return ev1.compareTo(ev2);
		else
			return nameComparision;
	}
}