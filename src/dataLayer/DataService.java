package dataLayer;

import java.sql.*;

public class DataService
{
	// connection string
	private static final String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=calendar;integratedSecurity=true;";
	
    // JDBC connection object  
	private Connection con = null;  

	public DataService()
	{
	}
	
	private void connect()
	{
		try
	    {  
			// establish the connection 
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
			catch(Exception e) {}  
		}
	}

	
	public void createEvent(Event event)
	{
		connect();

		String querry = "INSERT INTO events VALUES(?, ?, ?, ?);";

		try (PreparedStatement stmt = con.prepareStatement(querry))
		{
			stmt.setString(1, event.getName());
			stmt.setString(2, event.getStringDate());
			stmt.setString(3, event.getDescription());
			stmt.setString(4, event.getPlace());	
			
			stmt.executeUpdate();	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		disconnect();
	}

	public void deleteEvent(Event event)
	{
		connect();
		
		String querry = "DELETE FROM events WHERE name = ? AND dat = ? AND descrip = ? AND place = ?;";

		try (PreparedStatement stmt = con.prepareStatement(querry))
		{
			stmt.setString(1, event.getName());
			stmt.setString(2, event.getStringDate());
			stmt.setString(3, event.getDescription());
			stmt.setString(4, event.getPlace());	
			
			stmt.executeUpdate();		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		disconnect();
	}
	
}
