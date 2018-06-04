package dataLayer;

import java.util.Comparator;

/**
 *
 */
public class NameComparator implements Comparator<Event>
{
/**
 * Compares two Events names. 
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