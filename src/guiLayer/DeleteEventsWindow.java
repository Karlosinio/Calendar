package guiLayer;

import com.toedter.calendar.JCalendar;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.Font;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import dataLayer.Event;
import logicLayer.LogicLayerException;

/**
 * Class responsible for deleting events.
 */
@SuppressWarnings("serial")
public class DeleteEventsWindow extends JDialog
{
	private static DeleteEventsWindow dialog;
	
	private final JPanel contentPanel = new JPanel();
	private final JPanel buttonPane = new JPanel();

	private static JCalendar jCalendar;
	private static JSpinner spinnerHour;
	private static JSpinner spinnerMinute;
	
	/**
	 * Launch the application.
	 */
	public static void openWindow()
	{
		try
		{
			dialog = new DeleteEventsWindow(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private Calendar createCalendar()
	{
		Calendar calendar = GregorianCalendar.getInstance();	
		
		calendar = jCalendar.getCalendar();							

		calendar.set(Calendar.HOUR_OF_DAY, (int) spinnerHour.getValue());
		calendar.set(Calendar.MINUTE, (int) spinnerMinute.getValue());	
		
		return calendar;		
	}
	
	private DeleteEventsWindow(Event event)
	{
		getContentPane().setLocation(0, 13);	
		addWindowListener(new WindowAdapter()
        {	@Override
            public void windowClosing(WindowEvent e)
            {	MainWindow.openWindow(null);	}
        });
		
		setTitle("Add Event");
		setBounds(100, 100, 250, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		/////////////////////////////////////////////////////
		// Select date: label
		/////////////////////////////////////////////////////
		
		JLabel lblSelectDate = new JLabel("Select date:");
		lblSelectDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectDate.setBounds(12, 13, 208, 16);
		contentPanel.add(lblSelectDate);
				
		/////////////////////////////////////////////////////
		// Hour: label & spinner
		/////////////////////////////////////////////////////
		
		JLabel lblHour = new JLabel("Hour:");
		lblHour.setBounds(23, 75, 32, 16);
		contentPanel.add(lblHour);

		spinnerHour = new JSpinner();
		spinnerHour.setModel(new SpinnerNumberModel(12, 0, 23, 1));
		spinnerHour.setBounds(60, 72, 38, 22);
		contentPanel.add(spinnerHour);

		
		/////////////////////////////////////////////////////
		// Minute: label & spinner
		/////////////////////////////////////////////////////

		JLabel lblMinute = new JLabel("Minute:");
		lblMinute.setBounds(121, 75, 43, 16);
		contentPanel.add(lblMinute);

		spinnerMinute = new JSpinner();
		spinnerMinute.setModel(new SpinnerNumberModel(0, 0, 55, 5));
		spinnerMinute.setBounds(165, 72, 38, 22);
		contentPanel.add(spinnerMinute);		
		
		
		/////////////////////////////////////////////////////
		// Calendar: setting date
		/////////////////////////////////////////////////////	
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(23, 107, 180, 159);
		contentPanel.add(jCalendar);
		
		/////////////////////////////////////////////////////
		// OK button - add new event / accept changes to old event
		/////////////////////////////////////////////////////
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				Calendar cal = createCalendar();
		
				try
				{
					Main.ll.deleteOldEvents(cal);
					MainWindow.openWindow(cal);
					dialog.dispose();	
				}
				catch (LogicLayerException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
					
							
			}
		});		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		/////////////////////////////////////////////////////
		// Cancel button - discard all changes
		/////////////////////////////////////////////////////

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dialog.dispose();
				MainWindow.openWindow(null);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}	
}
