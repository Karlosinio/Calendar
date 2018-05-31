package guiLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataLayer.DataLayerException;
import dataLayer.Event;
import logicLayer.LogicLayer;

import java.awt.GridBagLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class mainWindow extends JFrame
{
	private JPanel contentPane;
	DefaultListModel<Event> eventsList = new DefaultListModel<Event>();
	//DefaultListModel<String> cal = new DefaultListModel<String>();

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
					mainWindow frame = new mainWindow();
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
	 */
	public mainWindow()
	{
		LogicLayer logicLayer = new LogicLayer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JCalendar calendar = new JCalendar();
		
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.gridheight = 13;
		gbc_calendar.gridwidth = 15;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 1;
		gbc_calendar.gridy = 1;
		contentPane.add(calendar, gbc_calendar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 5;
		gbc_scrollPane_1.gridheight = 9;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 13;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		

		
		//cal.addElement("cos");
		
		/////////////////////////////////////////////////////
		// List of Events
		/////////////////////////////////////////////////////
		
		//initEventsTest();
		
		JList<Event> list = new JList(eventsList);
		scrollPane_1.setViewportView(list);
		
		list.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Event) {
					((JLabel) renderer).setText(((Event) value).getName());
				}
				return renderer;
			}
		});
				
				JButton button = new JButton("Add Event");
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0)
					{
						NewEventWindow.OpenWindow();
						
					}
				});
						
						JScrollPane scrollPane_2 = new JScrollPane();
						GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
						gbc_scrollPane_2.gridheight = 9;
						gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
						gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
						gbc_scrollPane_2.gridx = 19;
						gbc_scrollPane_2.gridy = 1;
						contentPane.add(scrollPane_2, gbc_scrollPane_2);
						
						JLabel lblDetails = new JLabel("Details");
						scrollPane_2.setColumnHeaderView(lblDetails);
						
						JLabel lblEventsList = new JLabel("Events");
						scrollPane_2.setViewportView(lblEventsList);
						
				
						// List of events after clicked on calendar
						calendar.getDayChooser().getDayPanel().addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								DefaultListModel<Event> eventsList = logicLayer.getAllEventsFromDate(calendar.getDate());
								DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
								
								String result = "";
								
								for (int i = 0; i < eventsList.size(); i++) {
									result += eventsList.get(i).toString() + "\n";
								}
								lblEventsList.setText(result);
							}
						});
						
						// show event details
						list.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								Event event = list.getSelectedValue();
								lblEventsList.setText(event.toString());
							}
						});
						
						GridBagConstraints gbc_button = new GridBagConstraints();
						gbc_button.fill = GridBagConstraints.HORIZONTAL;
						gbc_button.gridwidth = 4;
						gbc_button.insets = new Insets(0, 0, 5, 5);
						gbc_button.gridx = 23;
						gbc_button.gridy = 1;
						contentPane.add(button, gbc_button);
	}
	
	private void initEventsTest() throws DataLayerException
	{
		Calendar cl = GregorianCalendar.getInstance();
		cl.set(2018, 1, 1, 23, 59, 0);
		
		//eventsList.addElement(new Event("Klusek ", cl, " ubra� ", " Kino "));
	}

}
