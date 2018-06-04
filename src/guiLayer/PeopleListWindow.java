package guiLayer;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;

import java.util.Calendar;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dataLayer.Person;
import logicLayer.LogicLayerException;

/**
 * Class responsible for list of people view.
 */
@SuppressWarnings("serial")
public class PeopleListWindow extends JDialog
{
	private static PeopleListWindow dialog;

	private final JPanel contentPanel = new JPanel();
	private JTextField tfFirstName;
	private JTextField tfLastName;

	/**
	 * Launch the application.
	 */
	public static void openWindow(Calendar calendar)
	{
		try
		{
			dialog = new PeopleListWindow(calendar);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void openWindow()
	{
		try
		{
			dialog = new PeopleListWindow(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	private PeopleListWindow(Calendar calendar)
	{
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {	MainWindow.openWindow(calendar);
            }
        });
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{	
					dialog.dispose();
					MainWindow.openWindow(calendar);
				}
			}
		});	

		/////////////////////////////////////////////////////
		// People: list with scroll pane
		/////////////////////////////////////////////////////
		
		setTitle("People List");
		setBounds(100, 100, 300, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);	
								
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 272, 311);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(scrollPane);
						
		JList<Person> list = new JList<Person>(Main.ll.getAllPeopleDLM());
		scrollPane.setViewportView(list);
				
		/////////////////////////////////////////////////////
		// People addition labels, fields and button
		/////////////////////////////////////////////////////
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(5, 329, 67, 16);
		contentPanel.add(lblFirstName);

		tfFirstName = new JTextField();
		tfFirstName.setBounds(77, 326, 200, 22);
		contentPanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(6, 356, 65, 16);
		contentPanel.add(lblLastName);

		tfLastName = new JTextField();
		tfLastName.setBounds(77, 353, 200, 22);
		tfLastName.setColumns(10);
		contentPanel.add(tfLastName);
		
		JButton btnAdd = new JButton("Add Person");
		btnAdd.setBounds(5, 380, 272, 25);
		contentPanel.add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{
				try
				{
					Main.ll.createPerson(tfFirstName.getText(), tfLastName.getText());	
					dialog.dispose();
					PeopleListWindow.openWindow(calendar);
				}
				catch(LogicLayerException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnAdd.addKeyListener(new KeyAdapter()
		{	@Override
			public void keyPressed(KeyEvent key)
			{
				if (key.getKeyCode()==KeyEvent.VK_ENTER)
				{	
					try
					{
						Main.ll.createPerson(tfFirstName.getText(), tfLastName.getText());	
						dialog.dispose();
						PeopleListWindow.openWindow(calendar);
					}
					catch(LogicLayerException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});


		/////////////////////////////////////////////////////
		// People addition labels, fields and button
		/////////////////////////////////////////////////////
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter()
		{	@Override
			public void mousePressed(MouseEvent arg0)
			{
				dialog.dispose();
				MainWindow.openWindow(calendar);
			}
		});
		okButton.addKeyListener(new KeyAdapter()
		{	@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{	
					dialog.dispose();
					MainWindow.openWindow(calendar);
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

	}

}
