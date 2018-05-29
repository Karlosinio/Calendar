package guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import logicLayer.LogicLayer;

import javax.swing.SpinnerNumberModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class NewEventWindow extends JDialog
{
	private static NewEventWindow dialog;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JCalendar jCalendar;
	
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	
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
		setTitle("Add Event");
		setBounds(100, 100, 576, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			jCalendar = new JCalendar();
			jCalendar.setBounds(15, 13, 180, 159);
			contentPanel.add(jCalendar);
		}
		{
			JLabel lblName = new JLabel("Name:");
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
			JButton button = new JButton("Select People");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					dialog.dispose();
					SelectPeopleWindow.OpenWindow();
				}
			});
			button.setBounds(413, 177, 125, 25);
			contentPanel.add(button);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(303, 124, 235, 45);
			contentPanel.add(scrollPane);
			{
				JList list = new JList();
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

							calendar.set(Calendar.HOUR_OF_DAY, spinner_1.getComponentCount());
							calendar.set(Calendar.MINUTE, spinner_2.getComponentCount());
							
							Main.ll.createEvent(textField.getText(), calendar, textField_1.getText(), textField_2.getText());
							
							dialog.dispose();
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JLabel label = new JLabel("Hour:");
			label.setBounds(15, 181, 32, 16);
			contentPanel.add(label);
		}
		{
			spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerNumberModel(12, 0, 23, 1));
			spinner_1.setBounds(52, 178, 38, 22);
			contentPanel.add(spinner_1);
		}
		{
			JLabel label = new JLabel("Minute:");
			label.setBounds(113, 181, 43, 16);
			contentPanel.add(label);
		}
		{
			spinner_2 = new JSpinner();
			spinner_2.setModel(new SpinnerNumberModel(0, 0, 55, 5));
			spinner_2.setBounds(157, 178, 38, 22);
			contentPanel.add(spinner_2);
		}
	}
}
