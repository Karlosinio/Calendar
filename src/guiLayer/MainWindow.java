package guiLayer;

import com.toedter.calendar.JCalendar;

import dataLayer.DataLayerException;
import dataLayer.Event;
import dataLayer.Person;
import logicLayer.ExportException;
import logicLayer.XMLSerializer;

import java.util.ArrayList;
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


@SuppressWarnings("serial")
public class MainWindow extends JFrame
{
	private JPanel contentPane;
	private static MainWindow frame;
	private static JCalendar jCalendar;
	private JButton btnNewButton;
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
	private JButton btnAllEvents;

	private static ArrayList<Event> eventsList;
	private static DefaultListModel<Event> eventsDLM;
	
	/**
	 * Launch the application.
	 */	
	public static void OpenWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new MainWindow();
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	void dateSelection()
	{
    	eventsList = Main.ll.getAllEventsFromDate(jCalendar.getCalendar()); 
		eventsDLM = new DefaultListModel<Event>();
		
		for(Event event : eventsList)
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
	
	void eventSelection(Event event)
	{
		tpName.setText(event.getName());
		tpDescription.setText(event.getDescription());
		tpPlace.setText(event.getPlace());
		tpTime.setText(event.getTime());
//		tpReminder.setText(event.getName());
	}
	
	void clearEventFields()
	{
		tpName.setText("");
		tpDescription.setText("");
		tpPlace.setText("");
		tpTime.setText("");		
	}
	
	/**
	 * Create the frame.
	 * @throws DataLayerException 
	 */
	public MainWindow() throws DataLayerException
	{
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {	
            	try
				{
					XMLSerializer.exportData("autosave.xml", Main.ll.getDataService() );
				}
            	catch (ExportException e)
				{
					ExceptionWindow.OpenWindow(e.getMessage());
				}
            	finally
            	{
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
		jCalendar.setBounds(45, 43, 581, 375);
		contentPane.add(jCalendar);	

		lblNewLabel = new JLabel("Events list:");
		lblNewLabel.setBounds(853, 43, 98, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel); 
		
		jCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener()
		{	@Override
		    public void propertyChange(PropertyChangeEvent e)
		    {	clearEventFields();
				dateSelection();			}
		});
		
		scrollPane = new JScrollPane();
	
		scrollPane.setBounds(701, 73, 402, 345);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);


		
	//	eventSelection(list.getSelectedValue());
		
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
		lblReminder.setBounds(713, 466, 60, 16);
		contentPane.add(lblReminder);
		
		tpReminder = new JTextPane();
		tpReminder.setBounds(778, 463, 325, 22);
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
	//	DefaultListModel dml = new DefaultListModel();
		
	//	for (Event event : eventsList )
	//	{
	//		dml.addElement(event.toString());
	//	}
		
		btnAllEvents = new JButton("All Events");
		btnAllEvents.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent e)
			{	AllEventsWindow.OpenWindow();	}
		});
		btnAllEvents.setBounds(701, 530, 402, 25);
		btnAllEvents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnAllEvents);
		
		btnNewButton = new JButton("Add Event");
		btnNewButton.setBounds(701, 560, 402, 102);
		btnNewButton.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{
				NewEventWindow.OpenWindow();
				frame.dispose();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPane.add(btnNewButton);
		

	}
}
