package guiLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataLayer.Event;
import logicLayer.LogicLayer;

import java.awt.GridBagLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class MainWindow extends JFrame
{
	private JPanel contentPane;

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
					MainWindow frame = new MainWindow();
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
	public MainWindow()
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				
				JButton button = new JButton("Add Event");
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0)
					{
						NewEventWindow.OpenWindow();
						
					}
				});
						
						JScrollPane scrollPane = new JScrollPane();
						GridBagConstraints gbc_scrollPane = new GridBagConstraints();
						gbc_scrollPane.gridwidth = 5;
						gbc_scrollPane.gridheight = 10;
						gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
						gbc_scrollPane.fill = GridBagConstraints.BOTH;
						gbc_scrollPane.gridx = 17;
						gbc_scrollPane.gridy = 3;
						contentPane.add(scrollPane, gbc_scrollPane);
				
						GridBagConstraints gbc_button = new GridBagConstraints();
						gbc_button.fill = GridBagConstraints.HORIZONTAL;
						gbc_button.gridwidth = 4;
						gbc_button.insets = new Insets(0, 0, 5, 5);
						gbc_button.gridx = 23;
						gbc_button.gridy = 1;
						contentPane.add(button, gbc_button);
	}

}
