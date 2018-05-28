package guiLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

import dataLayer.Event;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class mainWindow {

	private JFrame frmCalendar;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	DefaultListModel<Event> eventsList = new DefaultListModel<Event>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frmCalendar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() 
	{
		initComponents();
		initEvents();
	}

	
	/////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	// initializing components.
	////////////////////////////////////////////////////////////

	private void initComponents() 
	{
		frmCalendar = new JFrame();
		frmCalendar.getContentPane().setBackground(new Color(220, 220, 220));
		frmCalendar.setIconImage(Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		frmCalendar.setTitle("Calendar");
		frmCalendar.setBounds(300, 300, 770, 500);
		frmCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		frmCalendar.getContentPane().setLayout(gridBagLayout);
		
		JButton button_3 = new JButton("<");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 2;
		gbc_button_3.gridy = 1;
		frmCalendar.getContentPane().add(button_3, gbc_button_3);
		
		JLabel lblMiesic = new JLabel("Miesi\u0105c");
		lblMiesic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblMiesic = new GridBagConstraints();
		gbc_lblMiesic.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiesic.gridx = 3;
		gbc_lblMiesic.gridy = 1;
		frmCalendar.getContentPane().add(lblMiesic, gbc_lblMiesic);
		
		JSeparator separator_7 = new JSeparator();
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.anchor = GridBagConstraints.WEST;
		gbc_separator_7.insets = new Insets(0, 0, 5, 5);
		gbc_separator_7.gridx = 4;
		gbc_separator_7.gridy = 1;
		frmCalendar.getContentPane().add(separator_7, gbc_separator_7);
		
		JButton button_5 = new JButton(">");
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 5;
		gbc_button_5.gridy = 1;
		frmCalendar.getContentPane().add(button_5, gbc_button_5);
		
		JSeparator separator_6 = new JSeparator();
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 6;
		gbc_separator_6.gridy = 1;
		frmCalendar.getContentPane().add(separator_6, gbc_separator_6);
		
		JButton button_4 = new JButton("<");
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 8;
		gbc_button_4.gridy = 1;
		frmCalendar.getContentPane().add(button_4, gbc_button_4);
		
		JLabel lblRok = new JLabel("Rok");
		lblRok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblRok = new GridBagConstraints();
		gbc_lblRok.anchor = GridBagConstraints.NORTH;
		gbc_lblRok.insets = new Insets(0, 0, 5, 5);
		gbc_lblRok.gridx = 9;
		gbc_lblRok.gridy = 1;
		frmCalendar.getContentPane().add(lblRok, gbc_lblRok);
		
		JButton button_6 = new JButton(">");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 10;
		gbc_button_6.gridy = 1;
		frmCalendar.getContentPane().add(button_6, gbc_button_6);
		
		JSeparator separator_10 = new JSeparator();
		GridBagConstraints gbc_separator_10 = new GridBagConstraints();
		gbc_separator_10.anchor = GridBagConstraints.SOUTH;
		gbc_separator_10.insets = new Insets(0, 0, 5, 5);
		gbc_separator_10.gridx = 5;
		gbc_separator_10.gridy = 2;
		frmCalendar.getContentPane().add(separator_10, gbc_separator_10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(new Color(220, 220, 220));
		buttonGroup.add(btnNewButton);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		frmCalendar.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		buttonGroup.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 4;
		frmCalendar.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton button = new JButton("New button");
		button.setBackground(new Color(220, 220, 220));
		buttonGroup.add(button);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 5;
		gbc_button.gridy = 4;
		frmCalendar.getContentPane().add(button, gbc_button);
		
		JButton btnNewButton_2 = new JButton("New button");
		buttonGroup.add(btnNewButton_2);
		btnNewButton_2.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 7;
		gbc_btnNewButton_2.gridy = 4;
		frmCalendar.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		buttonGroup.add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 8;
		gbc_btnNewButton_3.gridy = 4;
		frmCalendar.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		buttonGroup.add(btnNewButton_4);
		btnNewButton_4.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 9;
		gbc_btnNewButton_4.gridy = 4;
		frmCalendar.getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		buttonGroup.add(btnNewButton_5);
		btnNewButton_5.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 10;
		gbc_btnNewButton_5.gridy = 4;
		frmCalendar.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.SOUTH;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 5;
		gbc_separator.gridy = 5;
		frmCalendar.getContentPane().add(separator, gbc_separator);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.anchor = GridBagConstraints.WEST;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 6;
		frmCalendar.getContentPane().add(separator_1, gbc_separator_1);
		
		JButton btnNewButton_6 = new JButton("New button");
		buttonGroup.add(btnNewButton_6);
		btnNewButton_6.setBackground(new Color(220, 220, 220));
		btnNewButton_6.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 2;
		gbc_btnNewButton_6.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_6, gbc_btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		buttonGroup.add(btnNewButton_7);
		btnNewButton_7.setBackground(new Color(220, 220, 220));
		btnNewButton_7.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 3;
		gbc_btnNewButton_7.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_7, gbc_btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("New button");
		buttonGroup.add(btnNewButton_8);
		btnNewButton_8.setBackground(new Color(220, 220, 220));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_8.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_8.gridx = 5;
		gbc_btnNewButton_8.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_8, gbc_btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		buttonGroup.add(btnNewButton_9);
		btnNewButton_9.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 7;
		gbc_btnNewButton_9.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_9, gbc_btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("New button");
		buttonGroup.add(btnNewButton_10);
		btnNewButton_10.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 8;
		gbc_btnNewButton_10.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_10, gbc_btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("New button");
		buttonGroup.add(btnNewButton_11);
		btnNewButton_11.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 9;
		gbc_btnNewButton_11.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_11, gbc_btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("New button");
		buttonGroup.add(btnNewButton_12);
		btnNewButton_12.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_12.gridx = 10;
		gbc_btnNewButton_12.gridy = 6;
		frmCalendar.getContentPane().add(btnNewButton_12, gbc_btnNewButton_12);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.anchor = GridBagConstraints.EAST;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 11;
		gbc_separator_2.gridy = 6;
		frmCalendar.getContentPane().add(separator_2, gbc_separator_2);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.anchor = GridBagConstraints.SOUTH;
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 5;
		gbc_separator_3.gridy = 7;
		frmCalendar.getContentPane().add(separator_3, gbc_separator_3);
		
		JSeparator separator_12 = new JSeparator();
		GridBagConstraints gbc_separator_12 = new GridBagConstraints();
		gbc_separator_12.anchor = GridBagConstraints.EAST;
		gbc_separator_12.insets = new Insets(0, 0, 5, 5);
		gbc_separator_12.gridx = 0;
		gbc_separator_12.gridy = 8;
		frmCalendar.getContentPane().add(separator_12, gbc_separator_12);
		
		JButton btnNewButton_13 = new JButton("New button");
		buttonGroup.add(btnNewButton_13);
		btnNewButton_13.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_13 = new GridBagConstraints();
		gbc_btnNewButton_13.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_13.gridx = 2;
		gbc_btnNewButton_13.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_13, gbc_btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("New button");
		buttonGroup.add(btnNewButton_14);
		btnNewButton_14.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_14 = new GridBagConstraints();
		gbc_btnNewButton_14.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_14.gridx = 3;
		gbc_btnNewButton_14.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_14, gbc_btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("New button");
		buttonGroup.add(btnNewButton_15);
		btnNewButton_15.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_15 = new GridBagConstraints();
		gbc_btnNewButton_15.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_15.gridx = 5;
		gbc_btnNewButton_15.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_15, gbc_btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("New button");
		buttonGroup.add(btnNewButton_16);
		btnNewButton_16.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_16 = new GridBagConstraints();
		gbc_btnNewButton_16.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_16.gridx = 7;
		gbc_btnNewButton_16.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_16, gbc_btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("New button");
		buttonGroup.add(btnNewButton_17);
		btnNewButton_17.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_17 = new GridBagConstraints();
		gbc_btnNewButton_17.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_17.gridx = 8;
		gbc_btnNewButton_17.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_17, gbc_btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton("New button");
		buttonGroup.add(btnNewButton_18);
		btnNewButton_18.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_18 = new GridBagConstraints();
		gbc_btnNewButton_18.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_18.gridx = 9;
		gbc_btnNewButton_18.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_18, gbc_btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("New button");
		buttonGroup.add(btnNewButton_19);
		btnNewButton_19.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_19 = new GridBagConstraints();
		gbc_btnNewButton_19.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_19.gridx = 10;
		gbc_btnNewButton_19.gridy = 8;
		frmCalendar.getContentPane().add(btnNewButton_19, gbc_btnNewButton_19);
		
		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.anchor = GridBagConstraints.SOUTH;
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 5;
		gbc_separator_4.gridy = 9;
		frmCalendar.getContentPane().add(separator_4, gbc_separator_4);
		
		JButton btnNewButton_20 = new JButton("New button");
		buttonGroup.add(btnNewButton_20);
		btnNewButton_20.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_20 = new GridBagConstraints();
		gbc_btnNewButton_20.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_20.gridx = 2;
		gbc_btnNewButton_20.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_20, gbc_btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("New button");
		buttonGroup.add(btnNewButton_21);
		btnNewButton_21.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_21 = new GridBagConstraints();
		gbc_btnNewButton_21.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_21.gridx = 3;
		gbc_btnNewButton_21.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_21, gbc_btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton("New button");
		buttonGroup.add(btnNewButton_22);
		btnNewButton_22.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_22 = new GridBagConstraints();
		gbc_btnNewButton_22.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_22.gridx = 5;
		gbc_btnNewButton_22.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_22, gbc_btnNewButton_22);
		
		JButton btnNewButton_23 = new JButton("New button");
		buttonGroup.add(btnNewButton_23);
		btnNewButton_23.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_23 = new GridBagConstraints();
		gbc_btnNewButton_23.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_23.gridx = 7;
		gbc_btnNewButton_23.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_23, gbc_btnNewButton_23);
		
		JButton btnNewButton_24 = new JButton("New button");
		buttonGroup.add(btnNewButton_24);
		btnNewButton_24.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_24 = new GridBagConstraints();
		gbc_btnNewButton_24.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_24.gridx = 8;
		gbc_btnNewButton_24.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_24, gbc_btnNewButton_24);
		
		JButton btnNewButton_25 = new JButton("New button");
		buttonGroup.add(btnNewButton_25);
		btnNewButton_25.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_25 = new GridBagConstraints();
		gbc_btnNewButton_25.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_25.gridx = 9;
		gbc_btnNewButton_25.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_25, gbc_btnNewButton_25);
		
		JButton btnNewButton_26 = new JButton("New button");
		buttonGroup.add(btnNewButton_26);
		btnNewButton_26.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_26 = new GridBagConstraints();
		gbc_btnNewButton_26.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_26.gridx = 10;
		gbc_btnNewButton_26.gridy = 10;
		frmCalendar.getContentPane().add(btnNewButton_26, gbc_btnNewButton_26);
		
		JSeparator separator_5 = new JSeparator();
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.anchor = GridBagConstraints.SOUTH;
		gbc_separator_5.insets = new Insets(0, 0, 5, 5);
		gbc_separator_5.gridx = 5;
		gbc_separator_5.gridy = 11;
		frmCalendar.getContentPane().add(separator_5, gbc_separator_5);
		
		JButton btnNewButton_27 = new JButton("New button");
		buttonGroup.add(btnNewButton_27);
		btnNewButton_27.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_27 = new GridBagConstraints();
		gbc_btnNewButton_27.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_27.gridx = 2;
		gbc_btnNewButton_27.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_27, gbc_btnNewButton_27);
		
		JButton btnNewButton_28 = new JButton("New button");
		buttonGroup.add(btnNewButton_28);
		btnNewButton_28.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_28 = new GridBagConstraints();
		gbc_btnNewButton_28.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_28.gridx = 3;
		gbc_btnNewButton_28.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_28, gbc_btnNewButton_28);
		
		JButton btnNewButton_29 = new JButton("New button");
		buttonGroup.add(btnNewButton_29);
		btnNewButton_29.setBackground(new Color(220, 220, 220));
		GridBagConstraints gbc_btnNewButton_29 = new GridBagConstraints();
		gbc_btnNewButton_29.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_29.gridx = 5;
		gbc_btnNewButton_29.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_29, gbc_btnNewButton_29);
		
		JButton btnNewButton_30 = new JButton("New button");
		btnNewButton_30.setBackground(new Color(220, 220, 220));
		buttonGroup.add(btnNewButton_30);
		GridBagConstraints gbc_btnNewButton_30 = new GridBagConstraints();
		gbc_btnNewButton_30.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_30.gridx = 7;
		gbc_btnNewButton_30.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_30, gbc_btnNewButton_30);
		
		JButton btnNewButton_31 = new JButton("New button");
		btnNewButton_31.setBackground(new Color(220, 220, 220));
		buttonGroup.add(btnNewButton_31);
		GridBagConstraints gbc_btnNewButton_31 = new GridBagConstraints();
		gbc_btnNewButton_31.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_31.gridx = 8;
		gbc_btnNewButton_31.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_31, gbc_btnNewButton_31);
		
		JButton btnNewButton_32 = new JButton("New button");
		btnNewButton_32.setBackground(new Color(220, 220, 220));
		buttonGroup.add(btnNewButton_32);
		GridBagConstraints gbc_btnNewButton_32 = new GridBagConstraints();
		gbc_btnNewButton_32.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_32.gridx = 9;
		gbc_btnNewButton_32.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_32, gbc_btnNewButton_32);
		
		JButton btnNewButton_33 = new JButton("New button");
		btnNewButton_33.setBackground(new Color(220, 220, 220));
		buttonGroup.add(btnNewButton_33);
		GridBagConstraints gbc_btnNewButton_33 = new GridBagConstraints();
		gbc_btnNewButton_33.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_33.gridx = 10;
		gbc_btnNewButton_33.gridy = 12;
		frmCalendar.getContentPane().add(btnNewButton_33, gbc_btnNewButton_33);
		
		JSeparator separator_9 = new JSeparator();
		GridBagConstraints gbc_separator_9 = new GridBagConstraints();
		gbc_separator_9.anchor = GridBagConstraints.SOUTH;
		gbc_separator_9.insets = new Insets(0, 0, 5, 5);
		gbc_separator_9.gridx = 2;
		gbc_separator_9.gridy = 13;
		frmCalendar.getContentPane().add(separator_9, gbc_separator_9);
		
		JSeparator separator_11 = new JSeparator();
		GridBagConstraints gbc_separator_11 = new GridBagConstraints();
		gbc_separator_11.insets = new Insets(0, 0, 5, 5);
		gbc_separator_11.gridx = 2;
		gbc_separator_11.gridy = 14;
		frmCalendar.getContentPane().add(separator_11, gbc_separator_11);
		
		JSeparator separator_8 = new JSeparator();
		GridBagConstraints gbc_separator_8 = new GridBagConstraints();
		gbc_separator_8.anchor = GridBagConstraints.SOUTH;
		gbc_separator_8.insets = new Insets(0, 0, 5, 5);
		gbc_separator_8.gridx = 2;
		gbc_separator_8.gridy = 15;
		frmCalendar.getContentPane().add(separator_8, gbc_separator_8);
		
		JLabel lblWydarzenie = new JLabel("Wydarzenia");
		lblWydarzenie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblWydarzenie = new GridBagConstraints();
		gbc_lblWydarzenie.insets = new Insets(0, 0, 5, 5);
		gbc_lblWydarzenie.gridx = 2;
		gbc_lblWydarzenie.gridy = 16;
		frmCalendar.getContentPane().add(lblWydarzenie, gbc_lblWydarzenie);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 17;
		frmCalendar.getContentPane().add(scrollPane, gbc_scrollPane);
				
		/////////////////////////////////////////////////////
		// List of Events
		/////////////////////////////////////////////////////
		
		JList<Event> list = new JList(eventsList);
		scrollPane.setViewportView(list);
		
		list.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Event) {
					// Here value will be of the Type 'CD'
					((JLabel) renderer).setText(((Event) value).getName());
				}
				return renderer;
			}
		});
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 7;
		gbc_scrollPane_1.gridy = 17;
		frmCalendar.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		JLabel lblSzczegy = new JLabel("Szczeg\u00F3\u0142y");
		lblSzczegy.setBackground(Color.WHITE);
		scrollPane_1.setColumnHeaderView(lblSzczegy);
		
		JLabel lblInformacje = new JLabel("Informacje");
		scrollPane_1.setViewportView(lblInformacje);
	
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Event event = list.getSelectedValue();
				lblInformacje.setText(event.toString());
			}
		});
		
		
		JButton btnDodajWydarzenie = new JButton("Dodaj");
		GridBagConstraints gbc_btnDodajWydarzenie = new GridBagConstraints();
		gbc_btnDodajWydarzenie.insets = new Insets(0, 0, 5, 5);
		gbc_btnDodajWydarzenie.gridx = 10;
		gbc_btnDodajWydarzenie.gridy = 17;
		frmCalendar.getContentPane().add(btnDodajWydarzenie, gbc_btnDodajWydarzenie);
		
		JMenuBar menuBar = new JMenuBar();
		frmCalendar.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmJn = new JMenuItem("Rzeczy");
		mnMenu.add(mntmJn);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Inne rzeczy");
		mnMenu.add(mntmNewMenuItem);
		
		JMenu mnAbout = new JMenu("Info");
		menuBar.add(mnAbout);
		
		JMenuItem mntmCo = new JMenuItem("About");
		mntmCo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Simple calendar app \nAuthors: \nJustyna Hubert 210200 \nKarol Podlewski 210294");
			}
		});
		mnAbout.add(mntmCo);
	}

	/////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events.
	////////////////////////////////////////////////////////////

	private void initEvents() 
	{
		Calendar cl = GregorianCalendar.getInstance();
		cl.set(2018, 1, 1, 23, 59, 0);
		
		eventsList.addElement(new Event("Klusek ", cl, " ubraæ ", " Kino "));
		eventsList.addElement(new Event("Puæ ", cl, " bleble ", " Kino "));
		eventsList.addElement(new Event("Dziab¹g ", cl, "dgfvdc ", "dvsv"));
	}

	
}
