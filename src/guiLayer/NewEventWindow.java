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

import dataLayer.DataLayerException;
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JCalendar jCalendar;
	JLabel lblName = new JLabel("Name:");
	
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JButton button;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JList list = new JList();
	private TreeMap<Integer, Person> people = Main.ll.getAllPeople();
	private DefaultListModel modelList = Main.ll.getAllPeopleDLM();
	private ArrayList<Person> peopleArray = new ArrayList<Person>();
	
	
	
	public static void OpenWindow()
	{
		try
		{
			dialog = new NewEventWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public NewEventWindow()
	{	
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {	MainWindow.OpenWindow();
            }
        });
		
		
		setTitle("Add Event");
		setBounds(100, 100, 575, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			jCalendar = new JCalendar();
			jCalendar.setBounds(22, 43, 180, 159);
			contentPanel.add(jCalendar);
		}
		{
			
			lblName.setBounds(260, 16, 38, 16);
			contentPanel.add(lblName);
		}
		{
			textField = new JTextField();
			textField.setBounds(303, 13, 235, 22);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			lblDescription.setBounds(230, 73, 68, 16);
			contentPanel.add(lblDescription);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(303, 70, 235, 22);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblPlace = new JLabel("Place:");
			lblPlace.setBounds(263, 100, 35, 16);
			contentPanel.add(lblPlace);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(303, 97, 235, 22);
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			JLabel label = new JLabel("People:");
			label.setBounds(255, 127, 43, 16);
			contentPanel.add(label);
		}
		{

			JButton btnAddNewPeople = new JButton("Add new people");
			btnAddNewPeople.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dialog.dispose();
					SelectPeopleWindow.OpenWindow();
				}
			});
			btnAddNewPeople.setBounds(413, 177, 125, 25);
			contentPanel.add(btnAddNewPeople);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(303, 124, 235, 45);
			contentPanel.add(scrollPane);
			{
							
				JList list = new JList(modelList);	
				
				list.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						String str = new String();
						
						for(Person person : people.values())
						{
							for(int i=0; i<peopleArray.size(); i++)
							{
								str += peopleArray.get(i).toString() + " ";
							}
							
							if(person.toString().equals((String)list.getSelectedValue()))
							{
								if(str.contains(person.toString()))
									continue;
								
								else
									peopleArray.add(person);
							}						
						}
					}
				});
				
				scrollPane.setViewportView(list);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add Event");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0)
					{
						Calendar calendar = GregorianCalendar.getInstance();
						try
						{
							calendar = jCalendar.getCalendar();							

							calendar.set(Calendar.HOUR_OF_DAY, (Integer) spinner_1.getValue());
							calendar.set(Calendar.MINUTE, (Integer) spinner_2.getValue());
		
							Main.ll.createEventWithPeople(textField.getText(), calendar, textField_1.getText(), textField_2.getText(), peopleArray);							
						
							dialog.dispose();
							MainWindow.OpenWindow();
						}
						catch(Exception e)
						{
							ExceptionWindow.OpenWindow(e.getMessage());
						}
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						dialog.dispose();
						MainWindow.OpenWindow();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	
		{
			JLabel label = new JLabel("Hour:");
			label.setBounds(22, 11, 32, 16);
			contentPanel.add(label);
		}
		{
			spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerNumberModel(12, 0, 23, 1));
			spinner_1.setBounds(59, 8, 38, 22);
			contentPanel.add(spinner_1);
		}
		{
			JLabel label = new JLabel("Minute:");
			label.setBounds(120, 11, 43, 16);
			contentPanel.add(label);
		}
		{
			spinner_2 = new JSpinner();
			spinner_2.setModel(new SpinnerNumberModel(0, 0, 55, 5));
			spinner_2.setBounds(164, 8, 38, 22);
			contentPanel.add(spinner_2);
		}
		
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
		
	}
}
