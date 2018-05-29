package dataLayer;

public class Person
{
	private String firstName;
	private String lastName;
	
	public Person(String firstName, String lastName) throws DataLayerException
	{
		super();
		
		if(firstName.isEmpty() || lastName.isEmpty())
			throw new DataLayerException("Values cannot be empty");	
			
		else
		{			
			this.firstName = firstName;
			this.lastName = lastName;	
		}
		
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return firstName + " " + lastName;
	}
	
}
