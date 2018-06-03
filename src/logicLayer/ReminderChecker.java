package logicLayer;

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.JOptionPane;

import dataLayer.Reminder;

public class ReminderChecker extends TimerTask
{	
	private LogicLayer ll;
	
	public ReminderChecker(LogicLayer ll)
	{
		this.ll = ll;
	}
	
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
