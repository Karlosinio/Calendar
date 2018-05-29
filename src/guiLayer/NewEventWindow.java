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
import com.toedter.calendar.JDateChooser;

import logicLayer.LogicLayer;

import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class NewEventWindow extends JDialog
{
	private static NewEventWindow dialog;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JDateChooser dateChooser;
	private JSpinner spinner_1;
	private JSpinner spinner_2;

	public static void OpenWindow()
	{
		try
		{
			dialog = new NewEventWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public NewEventWindow()
	{
		setTitle("Add Event");
		setBounds(100, 100, 450, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 1;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 11;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblDate = new JLabel("Date:");
			GridBagConstraints gbc_lblDate = new GridBagConstraints();
			gbc_lblDate.anchor = GridBagConstraints.EAST;
			gbc_lblDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblDate.gridx = 1;
			gbc_lblDate.gridy = 2;
			contentPanel.add(lblDate, gbc_lblDate);
		}
		{
			dateChooser = new JDateChooser();
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.gridwidth = 5;
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.gridx = 2;
			gbc_dateChooser.gridy = 2;
			contentPanel.add(dateChooser, gbc_dateChooser);
		}
		{
			JLabel lblHour = new JLabel("Hour:");
			GridBagConstraints gbc_lblHour = new GridBagConstraints();
			gbc_lblHour.anchor = GridBagConstraints.EAST;
			gbc_lblHour.insets = new Insets(0, 0, 5, 5);
			gbc_lblHour.gridx = 8;
			gbc_lblHour.gridy = 2;
			contentPanel.add(lblHour, gbc_lblHour);
		}
		{
			spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerNumberModel(12, 0, 23, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.gridwidth = 2;
			gbc_spinner.anchor = GridBagConstraints.WEST;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 9;
			gbc_spinner.gridy = 2;
			contentPanel.add(spinner_1, gbc_spinner);
		}
		{
			JLabel lblMinute = new JLabel("Minute:");
			GridBagConstraints gbc_lblMinute = new GridBagConstraints();
			gbc_lblMinute.anchor = GridBagConstraints.EAST;
			gbc_lblMinute.insets = new Insets(0, 0, 5, 5);
			gbc_lblMinute.gridx = 11;
			gbc_lblMinute.gridy = 2;
			contentPanel.add(lblMinute, gbc_lblMinute);
		}
		{
			spinner_2 = new JSpinner();
			spinner_2.setModel(new SpinnerNumberModel(0, 0, 55, 5));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.anchor = GridBagConstraints.WEST;
			gbc_spinner.insets = new Insets(0, 0, 5, 0);
			gbc_spinner.gridx = 12;
			gbc_spinner.gridy = 2;
			contentPanel.add(spinner_2, gbc_spinner);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.anchor = GridBagConstraints.EAST;
			gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescription.gridx = 1;
			gbc_lblDescription.gridy = 3;
			contentPanel.add(lblDescription, gbc_lblDescription);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.gridwidth = 11;
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.gridx = 2;
			gbc_textField_1.gridy = 3;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblPlace = new JLabel("Place:");
			GridBagConstraints gbc_lblPlace = new GridBagConstraints();
			gbc_lblPlace.anchor = GridBagConstraints.EAST;
			gbc_lblPlace.insets = new Insets(0, 0, 5, 5);
			gbc_lblPlace.gridx = 1;
			gbc_lblPlace.gridy = 4;
			contentPanel.add(lblPlace, gbc_lblPlace);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridwidth = 11;
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.gridx = 2;
			gbc_textField_2.gridy = 4;
			contentPanel.add(textField_2, gbc_textField_2);
		}
		{
			JLabel label = new JLabel("People:");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 5;
			contentPanel.add(label, gbc_label);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.gridwidth = 9;
			gbc_textField_3.insets = new Insets(0, 0, 0, 5);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 2;
			gbc_textField_3.gridy = 5;
			contentPanel.add(textField_3, gbc_textField_3);
		}
		{
			JButton btnAddPerson = new JButton("Select People");
			btnAddPerson.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e)
				{
					SelectPeopleWindow.OpenWindow();
				}
			});
			GridBagConstraints gbc_btnAddPerson = new GridBagConstraints();
			gbc_btnAddPerson.anchor = GridBagConstraints.EAST;
			gbc_btnAddPerson.gridwidth = 2;
			gbc_btnAddPerson.gridx = 11;
			gbc_btnAddPerson.gridy = 5;
			contentPanel.add(btnAddPerson, gbc_btnAddPerson);
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
							calendar = dateChooser.getCalendar();							

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
	}

}
