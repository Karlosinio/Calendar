package dataLayer;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ConnectionTest
{

	public ConnectionTest()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		Calendar cl = GregorianCalendar.getInstance();
		cl.set(2018, 1, 1, 23, 59, 0);
		Event event = new Event("Zdarzenie", cl, "Opis", "Miejsce");

		DataService ds = new DataService();
		
		// ds.createEvent(event);
		ds.deleteEvent(event);
	}

}
