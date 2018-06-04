package logicLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dataLayer.DataLayerException;
import dataLayer.DataService;
import dataLayer.Event;
import dataLayer.Person;
import dataLayer.Reminder;

/**
 * DBSerializer is responsible for data base serialization.
 */
public class DBSerializer extends Serializer
{
	private static final String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=calendar;integratedSecurity=true;";
	
	private String querry;
	
	private Connection con = null;  
	private Statement stmt = null; 
	private ResultSet rs = null;  
	
	private void connect()
	{
		try
	    {  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con = DriverManager.getConnection(connectionUrl);   
	    }
		catch (Exception e)
	    {
			e.printStackTrace();
		}   	
	}
	
	private void disconnect()
	{
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}  
		}
	}
	

	/**
	 * Exports data to data base.
	 */
	public void exportData(String fileName, DataService data) throws ExportException
	{
		connect();

		String[] querries = new String[]{"DELETE FROM people", "DELETE FROM events", "DELETE FROM events_people", "DELETE FROM reminders"};
		
		for (String qr : querries)
		{
			try (PreparedStatement stmt = con.prepareStatement(qr))
			{
				stmt.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}		
		}
		
		for (Person person : data.getAllPeople())
		{
			querry = "INSERT INTO people VALUES(?, ?, ?);";
			
			try (PreparedStatement stmt = con.prepareStatement(querry))
			{
				stmt.setInt(1, data.getAllPeople().indexOf(person));
				stmt.setString(2, person.getFirstName());
				stmt.setString(3, person.getLastName());
				
				stmt.executeUpdate();	
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}			
		}
		
		for(Event event : data.getAllEvents())
		{
			for (Person person : event.getPeopleList())
			{
				querry = "INSERT INTO events_people VALUES(?, ?);";
				
				try (PreparedStatement stmt = con.prepareStatement(querry))
				{
					stmt.setInt(1, data.getAllEvents().indexOf(event));
					stmt.setInt(2, data.getAllPeople().indexOf(person));	
					
					stmt.executeUpdate();	
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}			
			}
			
			querry = "INSERT INTO events VALUES(?, ?, ?, ?, ?);";
				
			try (PreparedStatement stmt = con.prepareStatement(querry))
			{
				stmt.setInt(1, data.getAllEvents().indexOf(event));
				stmt.setString(2, event.getName());
				stmt.setString(3, event.getDatabaseDate());
				stmt.setString(4, event.getDescription());
				stmt.setString(5, event.getPlace());	
				
				stmt.executeUpdate();	
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		for (Reminder reminder : data.getAllReminders())
		{
			querry = "INSERT INTO reminders VALUES(?, ?);";
			
			try (PreparedStatement stmt = con.prepareStatement(querry))
			{
				stmt.setInt(1, data.getAllEvents().indexOf(reminder.getEvent()));
				stmt.setString(2, reminder.getDatabaseDate());
				
				stmt.executeUpdate();	
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}			
		}

		disconnect();
	}

	
	
	/**
	 * Imports data from data base.
	 */
	public DataService importData(String fileName) throws ImportException
	{
		DataService data = new DataService();
		
		connect();
		
		try
		{			
			stmt = con.createStatement();  

			querry = "SELECT * FROM events";
	        rs = stmt.executeQuery(querry); 
	        
	        while (rs.next())
	        {	        	
	        	Calendar calendar = GregorianCalendar.getInstance();
	        	calendar.setTimeInMillis(rs.getTimestamp(3).getTime());
	        	
	        	data.createEvent(new Event(rs.getString(2), calendar ,rs.getString(4), rs.getString(5)));
	        }
	        
			querry = "SELECT * FROM people";
	        rs = stmt.executeQuery(querry);  
	        
	        while (rs.next())
	        	data.createPerson(new Person(rs.getString(2), rs.getString(3)));
	        
			querry = "SELECT * FROM events_people";
	        rs = stmt.executeQuery(querry);  
		
	        while (rs.next())
	        	data.addPeopleToEvent(data.getAllEvents().get(rs.getInt(1)), data.getAllPeople().get(rs.getInt(2)));

			querry = "SELECT * FROM reminders";
	        rs = stmt.executeQuery(querry);  
	        
	        while (rs.next())
	        {	        	
	        	Calendar calendar = GregorianCalendar.getInstance();
	        	calendar.setTimeInMillis(rs.getTimestamp(2).getTime());
	        	
	        	data.createReminder(new Reminder(calendar, data.getAllEvents().get(rs.getInt(1))));
	        }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} catch (DataLayerException e)
		{
			e.printStackTrace();
		}
		
		disconnect();
		
		return data;
	}
	
	/**
	 * Return String "Database".
	 */
	public String toString()
	{
		return "Database";
	}

	/**
	 * Return empty String.
	 */
	public String getFileFormat()
	{
		return "";
	}

}
