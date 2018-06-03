package guiLayer;

import com.toedter.calendar.JCalendar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.EventQueue;
import java.awt.Font;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Calendar;
import java.util.Timer;

import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import dataLayer.DataLayerException;
import dataLayer.Event;
import dataLayer.Person;
import logicLayer.ExportException;
import logicLayer.LogicLayerException;
import logicLayer.ReminderChecker;
import logicLayer.XMLSettingsSerializer;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;


@SuppressWarnings("serial")
public class MainWindow extends JFrame
{
	private static MainWindow frame;
	private static JCalendar jCalendar;
	
	private JScrollPane spEvents;
	private JLabel lblEventsList;
	private JList<Event> listEvents;

	private JLabel lblName;
	private JTextPane tpName;

	private JLabel lblTime;
	private JTextPane tpTime;
	
	private JLabel lblReminder;
	private JTextPane tpReminder;
	
	private JLabel lblDescription;
	private JTextPane tpDescription;
	
	private JLabel lblPlace;
	private JTextPane tpPlace;
	
	private JScrollPane spPeople;
	private JLabel lblPeople;
	private JList<Person> listPeople;
	private JMenuItem mntmExportToData;
	private JMenu mnEvents;
	private JMenuItem mntmDeleteOldEvents;
	private JSeparator separator;
	private JMenuItem mntmExportToFormat;


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
		DefaultListModel<Event> eventsDLM = Main.ll.getAllEventsFromDateDLM(jCalendar.getCalendar());

		listEvents = new JList<Event>(eventsDLM);
		listEvents.addMouseListener(new MouseAdapter()
		{	@Override
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
		tpReminder.setText(Main.ll.getReminderForEvent(event));
				
		listPeople = new JList<Person>(Main.ll.getAllPeopleFromEventDLM(event));
		spPeople.setViewportView(listPeople);
	}

	void clearEventFields()
	{
		tpName.setText("");
		tpDescription.setText("");
		tpPlace.setText("");
		tpTime.setText("");
		tpReminder.setText("");
		spPeople.setViewportView(listPeople);
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
				try
				{
					XMLSettingsSerializer.exportData(Main.ll.getSettings());
					Main.ll.getSerializer().exportData(Main.ll.getFinalFileName(), Main.ll.getDataService());
				}
				catch (ExportException e)
				{
					ExceptionWindow.openWindow(e.getMessage());
				} finally {
					frame.dispose();
				}
			}
		});

		setTitle("Calendar");
		setBounds(0, -18, 1200, 775);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/////////////////////////////////////////////////////
		// Menu bar
		/////////////////////////////////////////////////////		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1222, 26);
		contentPane.add(menuBar);
		
		JMenu mnSettings = new JMenu("File");
		menuBar.add(mnSettings);
		
		mntmExportToData = new JMenuItem("Export to PDF");
		mnSettings.add(mntmExportToData);
		
		separator = new JSeparator();
		mnSettings.add(separator);
		
		mntmExportToFormat = new JMenuItem("Export/Import Settings");
		mntmExportToFormat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SettingsWindow.openWindow();
			}
		});
		mnSettings.add(mntmExportToFormat);
		
		mnEvents = new JMenu("Events");
		menuBar.add(mnEvents);
		
		mntmDeleteOldEvents = new JMenuItem("Delete old events");
		mntmDeleteOldEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				frame.dispose();
				DeleteEventsWindow.openWindow();
			}
		});
		mnEvents.add(mntmDeleteOldEvents);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		JMenuItem mnHelpAbout = new JMenuItem("About");
		mnHelpAbout.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				AboutWindow.openWindow();
			}
		});
		mnHelp.add(mnHelpAbout);
		
		
		/////////////////////////////////////////////////////
		// Calendar: date selection -> filling events list
		/////////////////////////////////////////////////////	
		
		jCalendar = new JCalendar();
		
		if (calendar != null)
			jCalendar.setCalendar(calendar);
		
		jCalendar.setBounds(44, 58, 581, 375);
		contentPane.add(jCalendar);

		jCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener()
		{	@Override
			public void propertyChange(PropertyChangeEvent e)
			{
				clearEventFields();
				dateSelection();
			}
		});

		/////////////////////////////////////////////////////
		// Events list: label & list with scroll pane
		/////////////////////////////////////////////////////
		
		lblEventsList = new JLabel("Events list:");
		lblEventsList.setBounds(852, 58, 98, 25);
		lblEventsList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblEventsList);

		spEvents = new JScrollPane();
		spEvents.setBounds(700, 88, 402, 415);
		spEvents.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spEvents.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(spEvents);

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
		// Reminder: label, text field and timer
		/////////////////////////////////////////////////////
		
		lblReminder = new JLabel("Reminder:");
		lblReminder.setBounds(394, 514, 60, 16);
		contentPane.add(lblReminder);

		tpReminder = new JTextPane();
		tpReminder.setEditable(false);
		tpReminder.setBounds(459, 511, 146, 22);
		contentPane.add(tpReminder);
		
		Timer clock = new Timer();
		clock.schedule(new ReminderChecker(Main.ll), 0, 600000);

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

		listPeople = new JList<Person>();
		spPeople.setViewportView(listPeople);
		
		
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
				else
				{
					ExceptionWindow.openWindow("Event not selected");
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
					ExceptionWindow.openWindow("Event not selected");
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
		
		JButton btnAllPeople = new JButton("People List");
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
