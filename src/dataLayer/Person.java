package dataLayer;

import java.io.Serializable;

/**
 * Person represents a person with first name and last name. It can be add to peopleList in Event.
 */
@SuppressWarnings("serial")
public class Person implements Serializable
{
	private String firstName;
	private String lastName;
	
	/**
	 * Initializes a newly created Person object with specified first name and last name.
	 * @param firstName, which represents Person's firstName.
	 * @param lastName, which represents Person's lastName.
	 * @throws DataLayerException
	 */
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
	
	/**
	 * Getter for String firstName.
	 * @return Person's firstName.
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Set a new Person's firstName.  
	 * @param Person's firstName.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Getter for Person's last name.
	 * @return Person's lastName.
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Set a new Person's last name.
	 * @param Person's lastName.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Concatenates Person's first name and last name and converts to String.
	 */
	@Override
	public String toString()
	{
		return firstName + " " + lastName;
	}
	
}
