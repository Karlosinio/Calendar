package guiLayer;

import com.toedter.calendar.JCalendar;

import dataLayer.DataLayerException;
import dataLayer.Event;
import dataLayer.Person;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JLabel lblReminder;
	private JTextPane textPane_4;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JList list;
	private JScrollPane scrollPane_1;
	private JList<Person> list_1;
	private JButton btnAllEvents;

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

	/**
	 * Create the frame.
	 * @throws DataLayerException 
	 */
	public MainWindow() throws DataLayerException
	{
		setTitle("Calendar");
		setBounds(100, 100, 1201, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(45, 43, 581, 375);
		contentPane.add(jCalendar);	
		
		ArrayList<Event> eventsList = Main.ll.getAllEventsFromDate(jCalendar.getCalendar());
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		for(Event event : eventsList)
		{
			String string = new String();
			string += event.getDate().getTime();
			string += "  -  " + event.getName();
			
			dlm.addElement(string);
		}
		
		lblNewLabel = new JLabel("Events list:");
		lblNewLabel.setBounds(853, 43, 98, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(701, 73, 402, 345);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		list = new JList(dlm);
		scrollPane.setViewportView(list);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(95, 466, 38, 16);
		contentPane.add(lblName);
		
		textPane = new JTextPane();
		textPane.setBounds(138, 463, 258, 22);
		contentPane.add(textPane);
		
		lblTime = new JLabel("Time:");
		lblTime.setBounds(441, 466, 34, 16);
		contentPane.add(lblTime);
		
		textPane_3 = new JTextPane();
		textPane_3.setBounds(480, 463, 146, 22);
		contentPane.add(textPane_3);
		
		lblReminder = new JLabel("Reminder:");
		lblReminder.setBounds(713, 466, 60, 16);
		contentPane.add(lblReminder);
		
		textPane_4 = new JTextPane();
		textPane_4.setBounds(778, 463, 325, 22);
		contentPane.add(textPane_4);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(65, 534, 68, 16);
		contentPane.add(lblDescription);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(138, 530, 488, 25);
		contentPane.add(textPane_1);
		
		btnAllEvents = new JButton("All Events");
		btnAllEvents.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent e)
			{
				AllEventsWindow.OpenWindow();
			}
		});
		btnAllEvents.setBounds(701, 530, 402, 25);
		btnAllEvents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnAllEvents);
		
		lblPlace = new JLabel("Place:");
		lblPlace.setBounds(98, 563, 35, 16);
		contentPane.add(lblPlace);
		
		textPane_2 = new JTextPane();
		textPane_2.setBounds(138, 560, 488, 22);
		contentPane.add(textPane_2);
		
		btnNewButton = new JButton("Add Event");
		btnNewButton.setBounds(701, 560, 402, 102);
		btnNewButton.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{
				NewEventWindow.OpenWindow();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPane.add(btnNewButton);
		
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
		DefaultListModel dml = new DefaultListModel();
		
		for (Event event : eventsList )
		{
			dml.addElement(event.toString());
		}
	}
}
