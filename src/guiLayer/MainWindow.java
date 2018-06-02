package guiLayer;

import com.toedter.calendar.JCalendar;

import dataLayer.DataLayerException;
import dataLayer.Event;
import dataLayer.Person;
import logicLayer.ExportException;
import logicLayer.LogicLayerException;
import logicLayer.XMLSerializer;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JPanel contentPane;
	private static MainWindow frame;
	private static JCalendar jCalendar;
	private JLabel lblName;
	private JLabel lblDescription;
	private JLabel lblPlace;
	private JLabel lblPeople;
	private JLabel lblTime;
	private JLabel lblReminder;
	private JTextPane tpName;
	private JTextPane tpDescription;
	private JTextPane tpPlace;
	private JTextPane tpTime;
	private JTextPane tpReminder;
	private JLabel lblNewLabel;
	private JScrollPane spEvents;
	private JList<Event> listEvents;
	private JScrollPane spPeople;
	private JList<Person> listPeople;
	private JButton btnAllPeople;

	private static ArrayList<Event> eventsList;
	private static DefaultListModel<Event> eventsDLM;

	/**
	 * Launch the application.
	 */
	public static void openWindow(Calendar calendar) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainWindow(calendar);
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void dateSelection()
	{
		eventsList = Main.ll.getAllEventsFromDate(jCalendar.getCalendar());
		eventsDLM = new DefaultListModel<Event>();

		for (Event event : eventsList)
			eventsDLM.addElement(event);

		listEvents = new JList<Event>(eventsDLM);
		listEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				eventSelection(listEvents.getSelectedValue());
			}
		});
		spEvents.setViewportView(listEvents);
	}

	void eventSelection(Event event)
	{
		tpName.setText(event.getName());
		tpDescription.setText(event.getDescription());
		tpPlace.setText(event.getPlace());
		tpTime.setText(event.getTime());
		
		ArrayList<Person> array = event.getPeopleList();
		DefaultListModel peopleDLM = new DefaultListModel();
		
		for(int i=0; i<array.size(); i++)
			peopleDLM.addElement(array.get(i));
		
		
		JList<Person> list = new JList(peopleDLM);
		spPeople.setViewportView(list);
	}

	void clearEventFields()
	{
		tpName.setText("");
		tpDescription.setText("");
		tpPlace.setText("");
		tpTime.setText("");
		DefaultListModel clean = new DefaultListModel();
		JList j = new JList(clean);
		spPeople.setViewportView(j);
	}
	
	
	/**
	 * Create the frame.
	 * 
	 * @throws DataLayerException
	 */
	private MainWindow(Calendar calendar) throws DataLayerException
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				try {
					XMLSerializer.exportData("autosave.xml", Main.ll.getDataService());
				} catch (ExportException e) {
					ExceptionWindow.openWindow(e.getMessage());
				} finally {
					frame.dispose();
				}
			}
		});

		setTitle("Calendar");
		setBounds(0, -18, 1200, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/////////////////////////////////////////////////////
		// Menu bar
		/////////////////////////////////////////////////////		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1222, 26);
		contentPane.add(menuBar);
		
		
		/////////////////////////////////////////////////////
		// Calendar: date selection -> filling events list
		/////////////////////////////////////////////////////	
		
		jCalendar = new JCalendar();
		
		if (calendar != null)
			jCalendar.setCalendar(calendar);
		
		jCalendar.setBounds(44, 58, 581, 375);
		contentPane.add(jCalendar);

		lblNewLabel = new JLabel("Events list:");
		lblNewLabel.setBounds(852, 58, 98, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);

		jCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				clearEventFields();
				dateSelection();
			}
		});

		spEvents = new JScrollPane();

		spEvents.setBounds(700, 88, 402, 415);
		spEvents.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spEvents.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(spEvents);

		// eventSelection(list.getSelectedValue());

		dateSelection();

		/////////////////////////////////////////////////////
		// Name: label & text field
		/////////////////////////////////////////////////////
		
		lblName = new JLabel("Name:");
		lblName.setBounds(74, 484, 38, 16);
		contentPane.add(lblName);

		tpName = new JTextPane();
		tpName.setEditable(false);
		tpName.setBounds(117, 481, 258, 22);
		contentPane.add(tpName);

		/////////////////////////////////////////////////////
		// Time: label & text field
		/////////////////////////////////////////////////////
		
		lblTime = new JLabel("Time:");
		lblTime.setBounds(420, 484, 34, 16);
		contentPane.add(lblTime);

		tpTime = new JTextPane();
		tpTime.setEditable(false);
		tpTime.setBounds(459, 481, 146, 22);
		contentPane.add(tpTime);

		/////////////////////////////////////////////////////
		// Reminder: label & text field
		/////////////////////////////////////////////////////
		
		lblReminder = new JLabel("Reminder:");
		lblReminder.setBounds(394, 514, 60, 16);
		contentPane.add(lblReminder);

		tpReminder = new JTextPane();
		tpReminder.setEditable(false);
		tpReminder.setBounds(459, 511, 146, 22);
		contentPane.add(tpReminder);

		/////////////////////////////////////////////////////
		// Description: label & text field
		/////////////////////////////////////////////////////
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(44, 544, 68, 16);
		contentPane.add(lblDescription);

		tpDescription = new JTextPane();
		tpDescription.setEditable(false);
		tpDescription.setBounds(117, 541, 488, 22);
		contentPane.add(tpDescription);

		/////////////////////////////////////////////////////
		// Place: label & text field
		/////////////////////////////////////////////////////
		
		lblPlace = new JLabel("Place:");
		lblPlace.setBounds(77, 574, 35, 16);
		contentPane.add(lblPlace);

		tpPlace = new JTextPane();
		tpPlace.setEditable(false);
		tpPlace.setBounds(117, 571, 488, 22);
		contentPane.add(tpPlace);

		/////////////////////////////////////////////////////
		// People: label & list (with scroll pane)
		/////////////////////////////////////////////////////
		
		lblPeople = new JLabel("People:");
		lblPeople.setBounds(69, 604, 43, 16);
		contentPane.add(lblPeople);

		spPeople = new JScrollPane();
		spPeople.setBounds(117, 601, 488, 75);
		spPeople.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPeople.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(spPeople);

		listPeople = new JList();
		spPeople.setViewportView(listPeople);
		// DefaultListModel dml = new DefaultListModel();

		// for (Event event : eventsList )
		// {
		// dml.addElement(event.toString());
		// }

		
		/////////////////////////////////////////////////////
		// Edit event: button
		/////////////////////////////////////////////////////
		
		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditEvent.setBounds(700, 516, 180, 75);
		btnEditEvent.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{
				if (listEvents.getSelectedValue() != null)
				{
					NewEventWindow.openWindow(listEvents.getSelectedValue());
					frame.dispose();				
				}
			}
		});
		contentPane.add(btnEditEvent);
		
	
		/////////////////////////////////////////////////////
		// Delete event: button
		/////////////////////////////////////////////////////
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteEvent.setBounds(700, 606, 180, 75);
		btnDeleteEvent.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{	try
				{
					Main.ll.deleteEvent(listEvents.getSelectedValue());
					dateSelection();
					clearEventFields();
				}
				catch (LogicLayerException e)
				{
					ExceptionWindow.openWindow(e.getMessage());
				}
			}
		});
		contentPane.add(btnDeleteEvent);
	
		
		/////////////////////////////////////////////////////
		// Create event: button
		/////////////////////////////////////////////////////
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateEvent.setBounds(922, 516, 180, 75);
		btnCreateEvent.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{	NewEventWindow.openWindow();
				frame.dispose();
			}
		});
		contentPane.add(btnCreateEvent);
		
	
		/////////////////////////////////////////////////////
		// People list: button
		/////////////////////////////////////////////////////
		
		btnAllPeople = new JButton("People List");
		btnAllPeople.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent e)
			{
				frame.dispose();
				PeopleListWindow.openWindow();
			}
		});
		btnAllPeople.setBounds(922, 606, 180, 75);
		btnAllPeople.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnAllPeople);

	}
}
