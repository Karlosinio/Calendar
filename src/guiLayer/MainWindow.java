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
	private JScrollPane scrollPane;
	private JList<Event> list;
	private JScrollPane scrollPane_1;
	private JList<Person> list_1;
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

	void dateSelection() {
		eventsList = Main.ll.getAllEventsFromDate(jCalendar.getCalendar());
		eventsDLM = new DefaultListModel<Event>();

		for (Event event : eventsList)
			eventsDLM.addElement(event);

		list = new JList<Event>(eventsDLM);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				eventSelection(list.getSelectedValue());
			}
		});
		scrollPane.setViewportView(list);
	}

	void eventSelection(Event event) {
		tpName.setText(event.getName());
		tpDescription.setText(event.getDescription());
		tpPlace.setText(event.getPlace());
		tpTime.setText(event.getTime());
		
		ArrayList<Person> array = event.getPeopleList();
		DefaultListModel peopleDLM = new DefaultListModel();
		
		for(int i=0; i<array.size(); i++)
			peopleDLM.addElement(array.get(i));
		
		
		JList<Person> list = new JList(peopleDLM);
		scrollPane_1.setViewportView(list);
	}

	void clearEventFields()
	{
		tpName.setText("");
		tpDescription.setText("");
		tpPlace.setText("");
		tpTime.setText("");
		DefaultListModel clean = new DefaultListModel();
		JList j = new JList(clean);
		scrollPane_1.setViewportView(j);
	}
	
	
	/**
	 * Create the frame.
	 * 
	 * @throws DataLayerException
	 */
	public MainWindow(Calendar calendar) throws DataLayerException
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				try {
					XMLSerializer.exportData("autosave.xml", Main.ll.getDataService());
				} catch (ExportException e) {
					ExceptionWindow.OpenWindow(e.getMessage());
				} finally {
					frame.dispose();
				}
			}
		});

		setTitle("Calendar");
		setBounds(100, 100, 1201, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jCalendar = new JCalendar();
		
		if (calendar != null)
			jCalendar.setCalendar(calendar);
		
		jCalendar.setBounds(45, 43, 581, 375);
		contentPane.add(jCalendar);

		lblNewLabel = new JLabel("Events list:");
		lblNewLabel.setBounds(853, 43, 98, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);

		jCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				clearEventFields();
				dateSelection();
			}
		});

		scrollPane = new JScrollPane();

		scrollPane.setBounds(701, 73, 402, 415);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);

		// eventSelection(list.getSelectedValue());

		dateSelection();

		lblName = new JLabel("Name:");
		lblName.setBounds(95, 466, 38, 16);
		contentPane.add(lblName);

		tpName = new JTextPane();
		tpName.setEditable(false);
		tpName.setBounds(138, 463, 258, 22);
		contentPane.add(tpName);

		lblTime = new JLabel("Time:");
		lblTime.setBounds(441, 466, 34, 16);
		contentPane.add(lblTime);

		tpTime = new JTextPane();
		tpTime.setEditable(false);
		tpTime.setBounds(480, 463, 146, 22);
		contentPane.add(tpTime);

		lblReminder = new JLabel("Reminder:");
		lblReminder.setBounds(415, 495, 60, 16);
		contentPane.add(lblReminder);

		tpReminder = new JTextPane();
		tpReminder.setEditable(false);
		tpReminder.setBounds(480, 495, 146, 22);
		contentPane.add(tpReminder);

		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(65, 534, 68, 16);
		contentPane.add(lblDescription);

		tpDescription = new JTextPane();
		tpDescription.setEditable(false);
		tpDescription.setBounds(138, 530, 488, 25);
		contentPane.add(tpDescription);

		lblPlace = new JLabel("Place:");
		lblPlace.setBounds(98, 563, 35, 16);
		contentPane.add(lblPlace);

		tpPlace = new JTextPane();
		tpPlace.setEditable(false);
		tpPlace.setBounds(138, 560, 488, 22);
		contentPane.add(tpPlace);

		lblPeople = new JLabel("People:");
		lblPeople.setBounds(90, 587, 43, 16);
		contentPane.add(lblPeople);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(138, 587, 488, 75);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_1);

		list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		// DefaultListModel dml = new DefaultListModel();

		// for (Event event : eventsList )
		// {
		// dml.addElement(event.toString());
		// }

		btnAllPeople = new JButton("All People");
		btnAllPeople.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AllEventsWindow.OpenWindow();
			}
		});
		btnAllPeople.setBounds(923, 587, 180, 75);
		btnAllPeople.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnAllPeople);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteEvent.setBounds(701, 502, 180, 75);
		btnDeleteEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try
				{
					Main.ll.deleteEvent(list.getSelectedValue());
					dateSelection();
					clearEventFields();
				}
				catch (LogicLayerException e)
				{
					ExceptionWindow.OpenWindow(e.getMessage());
				}
			}
		});
		contentPane.add(btnDeleteEvent);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateEvent.setBounds(923, 502, 180, 75);
		btnCreateEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				NewEventWindow.OpenWindow();
				frame.dispose();
			}
		});

		contentPane.add(btnCreateEvent);
		
		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditEvent.setBounds(701, 587, 180, 75);
		btnEditEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (list.getSelectedValue() != null)
				{
					NewEventWindow.OpenWindow(list.getSelectedValue());
					frame.dispose();				
				}

			}
		});
		
		
		
		contentPane.add(btnEditEvent);

	}
}
