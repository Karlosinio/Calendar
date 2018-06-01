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



import javax.swing.SpinnerNumberModel;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.ArrayList;

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
	private static JTextField tfName;
	private static JTextField tfDescription;
	private static JTextField tfPlace;

	private static JCalendar jCalendar;
	
	
	private static JSpinner spinnerHour;
	private static JSpinner spinnerMinute;
	private JButton button;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JList list = new JList();
	private ArrayList<Person> people = Main.ll.getAllPeople();
	private DefaultListModel modelList = Main.ll.getAllPeopleDLM();
	private static ArrayList<Person> peopleArray = new ArrayList<Person>();
	
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
	
	public static Calendar createEvent()
	{
		Calendar calendar = GregorianCalendar.getInstance();

		try
		{
			calendar = jCalendar.getCalendar();							

			calendar.set(Calendar.HOUR_OF_DAY, (int) spinnerHour.getValue());
			calendar.set(Calendar.MINUTE, (int) spinnerMinute.getValue());

			Main.ll.createEventWithPeople(tfName.getText(), calendar, tfDescription.getText(), tfPlace.getText(), peopleArray);							
		}
		catch(Exception e)
		{
			ExceptionWindow.openWindow(e.getMessage());
		}
		
		return calendar;
	}
	
	public static Calendar createEvent(Event event)
	{
		event.setName(tfName.getText());
		event.setDescription(tfDescription.getText());
		event.setPlace(tfPlace.getText());
		
		event.setCalendar(jCalendar.getCalendar());
		event.setHour((int) spinnerHour.getValue());
		event.setMinute((int) spinnerMinute.getValue());	
		
		return event.getCalendar();
	}
	
	public NewEventWindow(Event event)
	{	
		addWindowListener(new WindowAdapter()
        {	@Override
            public void windowClosing(WindowEvent e)
            {	MainWindow.openWindow(null);	}
        });
		
		setTitle("Add Event");
		setBounds(100, 100, 575, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

				
		/////////////////////////////////////////////////////
		// Hour: label & spinner
		/////////////////////////////////////////////////////
		
		JLabel lblHour = new JLabel("Hour:");
		lblHour.setBounds(22, 11, 32, 16);
		contentPanel.add(lblHour);

		spinnerHour = new JSpinner();
		spinnerHour.setModel(new SpinnerNumberModel(12, 0, 23, 1));
		spinnerHour.setBounds(59, 8, 38, 22);
		contentPanel.add(spinnerHour);

		
		/////////////////////////////////////////////////////
		// Minute: label & spinner
		/////////////////////////////////////////////////////

		JLabel lblMinute = new JLabel("Minute:");
		lblMinute.setBounds(120, 11, 43, 16);
		contentPanel.add(lblMinute);

		spinnerMinute = new JSpinner();
		spinnerMinute.setModel(new SpinnerNumberModel(0, 0, 55, 5));
		spinnerMinute.setBounds(164, 8, 38, 22);
		contentPanel.add(spinnerMinute);		
		
		
		/////////////////////////////////////////////////////
		// Calendar: setting date
		/////////////////////////////////////////////////////	
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(22, 43, 180, 159);
		contentPanel.add(jCalendar);

		
		/////////////////////////////////////////////////////
		// Name: label & text field
		/////////////////////////////////////////////////////
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(260, 16, 38, 16);
		contentPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(303, 13, 235, 22);
		tfName.setColumns(10);
		contentPanel.add(tfName);

		
		/////////////////////////////////////////////////////
		// Description: label & text field
		/////////////////////////////////////////////////////
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(230, 73, 68, 16);
		contentPanel.add(lblDescription);

		tfDescription = new JTextField();
		tfDescription.setBounds(303, 70, 235, 22);
		tfDescription.setColumns(10);
		contentPanel.add(tfDescription);
		
		
		/////////////////////////////////////////////////////
		// Place: label & text field
		/////////////////////////////////////////////////////
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(263, 100, 35, 16);
		contentPanel.add(lblPlace);

		tfPlace = new JTextField();
		tfPlace.setBounds(303, 97, 235, 22);
		tfPlace.setColumns(10);
		contentPanel.add(tfPlace);

		
		/////////////////////////////////////////////////////
		// People: label, list (with scroll pane) & button
		/////////////////////////////////////////////////////
		
		JLabel label = new JLabel("People:");
		label.setBounds(255, 127, 43, 16);
		contentPanel.add(label);


		JButton btnAddNewPeople = new JButton("Add new people");
		btnAddNewPeople.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dialog.dispose();
				SelectPeopleWindow.openWindow();
			}
		});
		btnAddNewPeople.setBounds(413, 177, 125, 25);
		contentPanel.add(btnAddNewPeople);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(303, 124, 235, 45);
		contentPanel.add(scrollPane);

							
		JList listPeople = new JList(modelList);	
		
		// adding people to event
		listPeople.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(Person person : people)
				{
					if(person.toString().equals((String)listPeople.getSelectedValue()))
						peopleArray.add(person);
				}
			}
		});
		
		scrollPane.setViewportView(listPeople);


		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Add Event");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{			
				if (event != null)
				{
					createEvent(event);
					MainWindow.openWindow(event.getCalendar());
				}
					
				else
				{
					createEvent();
					MainWindow.openWindow(jCalendar.getCalendar());
				}
					
				dialog.dispose();				
			}
		});
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		

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
		// Reminders: radio buttons
		/////////////////////////////////////////////////////
		
		JLabel lblReminder = new JLabel("Reminder:");
		lblReminder.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReminder.setBounds(12, 213, 78, 16);
		contentPanel.add(lblReminder);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		rdbtnNone.setSelected(true);
		buttonGroup.add(rdbtnNone);
		rdbtnNone.setBounds(98, 209, 68, 25);
		contentPanel.add(rdbtnNone);
		
		JRadioButton rdbtnMin = new JRadioButton("5 min");
		buttonGroup.add(rdbtnMin);
		rdbtnMin.setBounds(167, 209, 68, 25);
		contentPanel.add(rdbtnMin);
		
		JRadioButton rdbtnMin_1 = new JRadioButton("30 min");
		buttonGroup.add(rdbtnMin_1);
		rdbtnMin_1.setBounds(239, 209, 78, 25);
		contentPanel.add(rdbtnMin_1);
		
		JRadioButton rdbtnHour = new JRadioButton("1 hour");
		buttonGroup.add(rdbtnHour);
		rdbtnHour.setBounds(314, 209, 68, 25);
		contentPanel.add(rdbtnHour);
		
		JRadioButton rdbtnHours = new JRadioButton("2 hours");
		buttonGroup.add(rdbtnHours);
		rdbtnHours.setBounds(386, 209, 78, 25);
		contentPanel.add(rdbtnHours);
		
		JRadioButton rdbtnDay = new JRadioButton("1 day");
		buttonGroup.add(rdbtnDay);
		rdbtnDay.setBounds(464, 209, 59, 25);
		contentPanel.add(rdbtnDay);
		
		/////////////////////////////////////////////////////
		// Set fields for edited event
		/////////////////////////////////////////////////////
		
		if (event != null)
		{
			okButton.setText("Change Event");
			spinnerHour.setValue(event.getHour());
			spinnerMinute.setValue(event.getMinute());
			jCalendar.setCalendar(event.getCalendar());
			tfName.setText(event.getName());
			tfDescription.setText(event.getDescription());
			tfPlace.setText(event.getPlace());
		}
	}	

}
