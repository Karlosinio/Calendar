package guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JList;
import javax.swing.JScrollPane;


import com.toedter.calendar.JCalendar;

import dataLayer.Event;
import dataLayer.Person;
import logicLayer.LogicLayerException;

import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


@SuppressWarnings("serial")
public class NewEventWindow extends JDialog
{
	private static NewEventWindow dialog;
	
	private final JPanel contentPanel = new JPanel();
	private final JPanel buttonPane = new JPanel();

	private static JCalendar jCalendar;
	private static JSpinner spinnerHour;
	private static JSpinner spinnerMinute;

	private static JTextField tfName;
	private static JTextField tfDescription;
	private static JTextField tfPlace;

	private static JList<Person> listEventPeople;
	private static JList<Person> listAllPeople;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	public static void openWindow()
	{
		try
		{
			dialog = new NewEventWindow(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void openWindow(Event event)
	{
		try
		{
			dialog = new NewEventWindow(event);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	private void setFields(Event event)
	{
		spinnerHour.setValue(event.getHour());
		spinnerMinute.setValue(event.getMinute());
		jCalendar.setCalendar(event.getCalendar());
		tfName.setText(event.getName());
		tfDescription.setText(event.getDescription());
		tfPlace.setText(event.getPlace());
	}
	
	private Calendar createCalendar()
	{
		Calendar calendar = GregorianCalendar.getInstance();	
		
		calendar = jCalendar.getCalendar();							

		calendar.set(Calendar.HOUR_OF_DAY, (int) spinnerHour.getValue());
		calendar.set(Calendar.MINUTE, (int) spinnerMinute.getValue());	
		
		return calendar;		
	}
	
	private NewEventWindow(Event event)
	{
		getContentPane().setLocation(0, 16);	
		addWindowListener(new WindowAdapter()
        {	@Override
            public void windowClosing(WindowEvent e)
            {	MainWindow.openWindow(null);	}
        });
		
		setTitle("Add Event");
		setBounds(100, 100, 900, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

				
		/////////////////////////////////////////////////////
		// Hour: label & spinner
		/////////////////////////////////////////////////////
		
		JLabel lblHour = new JLabel("Hour:");
		lblHour.setBounds(18, 33, 32, 16);
		contentPanel.add(lblHour);

		spinnerHour = new JSpinner();
		spinnerHour.setModel(new SpinnerNumberModel(12, 0, 23, 1));
		spinnerHour.setBounds(55, 30, 38, 22);
		contentPanel.add(spinnerHour);

		
		/////////////////////////////////////////////////////
		// Minute: label & spinner
		/////////////////////////////////////////////////////

		JLabel lblMinute = new JLabel("Minute:");
		lblMinute.setBounds(116, 33, 43, 16);
		contentPanel.add(lblMinute);

		spinnerMinute = new JSpinner();
		spinnerMinute.setModel(new SpinnerNumberModel(0, 0, 55, 5));
		spinnerMinute.setBounds(160, 30, 38, 22);
		contentPanel.add(spinnerMinute);		
		
		
		/////////////////////////////////////////////////////
		// Calendar: setting date
		/////////////////////////////////////////////////////	
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(18, 65, 180, 159);
		contentPanel.add(jCalendar);

		
		/////////////////////////////////////////////////////
		// Name: label & text field
		/////////////////////////////////////////////////////
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(256, 38, 38, 16);
		contentPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(299, 35, 235, 22);
		tfName.setColumns(10);
		contentPanel.add(tfName);

		
		/////////////////////////////////////////////////////
		// Description: label & text field
		/////////////////////////////////////////////////////
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(226, 95, 68, 16);
		contentPanel.add(lblDescription);

		tfDescription = new JTextField();
		tfDescription.setBounds(299, 92, 235, 22);
		tfDescription.setColumns(10);
		contentPanel.add(tfDescription);
		
		
		/////////////////////////////////////////////////////
		// Place: label & text field
		/////////////////////////////////////////////////////
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(259, 122, 35, 16);
		contentPanel.add(lblPlace);

		tfPlace = new JTextField();
		tfPlace.setBounds(299, 119, 235, 22);
		tfPlace.setColumns(10);
		contentPanel.add(tfPlace);

		
		/////////////////////////////////////////////////////
		// List of all People: label, list (with scroll pane)
		////////////////////////////////////////////////////
		
		JLabel lblListOfAll = new JLabel("List of all People:");
		lblListOfAll.setBounds(620, 95, 106, 16);
		contentPanel.add(lblListOfAll);
	
		JScrollPane spAllPeople = new JScrollPane();
		spAllPeople.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spAllPeople.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spAllPeople.setBounds(620, 114, 235, 110);
		contentPanel.add(spAllPeople);

		listAllPeople = new JList<Person>(Main.ll.getAllPeopleDLM());
		
		spAllPeople.setViewportView(listAllPeople);
		
		
		/////////////////////////////////////////////////////
		// People: label, list (with scroll pane)
		/////////////////////////////////////////////////////
		
		JLabel lblPeople = new JLabel("People:");
		lblPeople.setBounds(251, 149, 43, 16);
		contentPanel.add(lblPeople);

		JScrollPane spEventPeople = new JScrollPane();
		spEventPeople.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spEventPeople.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spEventPeople.setBounds(299, 146, 235, 78);
		contentPanel.add(spEventPeople);
			
		listEventPeople = new JList<Person>();
		ArrayList<Person> peopleToEvent = new ArrayList<Person>();
		DefaultListModel<Person> peopleDLM = new DefaultListModel<Person>();
		
		
		
		if (event != null)
			listEventPeople = new JList<Person>(Main.ll.getAllPeopleFromEventDLM(event));	
			
		
		spEventPeople.setViewportView(listEventPeople);

		
		/////////////////////////////////////////////////////
		// People buttons: add and remove people from event
		/////////////////////////////////////////////////////
		
		JButton btnAddToEvent = new JButton("<<");
		btnAddToEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String str = new String();

				for(Person person : peopleToEvent)
					str += person.toString();
					
				if(str.contains(listAllPeople.getSelectedValue().toString()))	{}
				
				else
				{
					peopleToEvent.add(listAllPeople.getSelectedValue());
					peopleDLM.addElement(listAllPeople.getSelectedValue());
					listEventPeople.setModel(peopleDLM);
				}				
			}
		});		
		btnAddToEvent.setBounds(546, 155, 60, 25);
		contentPanel.add(btnAddToEvent);
		
		
		JButton btnRemoveFromEvent = new JButton(">>");
		btnRemoveFromEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(listEventPeople.getSelectedValue()!=null)
				{
					peopleToEvent.remove(listEventPeople.getSelectedValue());
					peopleDLM.removeElement(listEventPeople.getSelectedValue());
					listEventPeople.setModel(peopleDLM);	
				}
			
			}
		});
		btnRemoveFromEvent.setBounds(546, 190, 60, 25);
		contentPanel.add(btnRemoveFromEvent);		

	
		/////////////////////////////////////////////////////
		// Reminders: radio buttons
		/////////////////////////////////////////////////////
		
		JLabel lblReminder = new JLabel("Reminder:");
		lblReminder.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReminder.setBounds(554, 37, 78, 16);
		contentPanel.add(lblReminder);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		rdbtnNone.setSelected(true);
		buttonGroup.add(rdbtnNone);
		rdbtnNone.setBounds(636, 34, 68, 24);
		contentPanel.add(rdbtnNone);
		
		JRadioButton rdbtnMin = new JRadioButton("5 min");
		buttonGroup.add(rdbtnMin);
		rdbtnMin.setBounds(716, 34, 68, 24);
		contentPanel.add(rdbtnMin);
		
		JRadioButton rdbtnMin_1 = new JRadioButton("30 min");
		buttonGroup.add(rdbtnMin_1);
		rdbtnMin_1.setBounds(796, 35, 78, 24);
		contentPanel.add(rdbtnMin_1);
		
		JRadioButton rdbtnHour = new JRadioButton("1 hour");
		buttonGroup.add(rdbtnHour);
		rdbtnHour.setBounds(636, 55, 68, 24);
		contentPanel.add(rdbtnHour);
		
		JRadioButton rdbtnHours = new JRadioButton("2 hours");
		buttonGroup.add(rdbtnHours);
		rdbtnHours.setBounds(716, 55, 78, 24);
		contentPanel.add(rdbtnHours);
		
		JRadioButton rdbtnDay = new JRadioButton("1 day");
		buttonGroup.add(rdbtnDay);
		rdbtnDay.setBounds(796, 55, 59, 24);
		contentPanel.add(rdbtnDay);
		
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
					if (event == null)
						Main.ll.createEventWithPeople(tfName.getText(), cal, tfDescription.getText(), tfPlace.getText(), peopleToEvent);

					else
						Main.ll.updateEvent(event, tfName.getText(), cal, tfDescription.getText(), tfPlace.getText(), peopleToEvent);
					
					MainWindow.openWindow(cal);
					dialog.dispose();	
				}
				catch (LogicLayerException e)
				{
					ExceptionWindow.openWindow(e.getMessage());
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

		/////////////////////////////////////////////////////
		// Set fields for edited event
		/////////////////////////////////////////////////////
		
		if (event != null)
			setFields(event);

	}	
}
