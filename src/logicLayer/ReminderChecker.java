package logicLayer;

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.JOptionPane;

import dataLayer.Reminder;

/**
 * ReminderChecker is responsible for checking upcoming Event.
 */
public class ReminderChecker extends TimerTask
{	
	private LogicLayer ll;
	
	/**
	 * Initializes a newly created ReminderChecker object with specified LogicLayer.
	 * @param ll, which represents LogicLayer.
	 */
	public ReminderChecker(LogicLayer ll)
	{
		this.ll = ll;
	}
	
	/**
	 * Compares actual time with time of upcoming Reminder. If they're equal, shows message dialog with information about upcoming Event.
	 */
	@Override
	public void run()
	{
		Calendar calendar = Calendar.getInstance();
		
		for(Reminder reminder : ll.getAllReminders())
		{
			if (reminder.compareTo(calendar) == 0) 
			{				
				Toolkit.getDefaultToolkit().beep();
				String result = "Upcoming event: " + reminder.getEvent().getName();
				JOptionPane.showMessageDialog(null, result);
			}			
		}		
	}
}
