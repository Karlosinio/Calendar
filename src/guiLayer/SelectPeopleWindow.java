package guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logicLayer.LogicLayer;
import logicLayer.LogicLayerException;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class SelectPeopleWindow extends JDialog
{
	private static SelectPeopleWindow dialog;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void openWindow()
	{
		try
		{
			dialog = new SelectPeopleWindow();
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
	public SelectPeopleWindow()
	{	
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {	NewEventWindow.openWindow();
            }
        });
		
		
		setTitle("Select People");
		setBounds(100, 100, 300, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);	
		{
			{
				DefaultListModel modelList = Main.ll.getAllPeopleDLM();
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridwidth = 3;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				contentPanel.add(scrollPane, gbc_scrollPane);
				
				JList list = new JList(modelList);
				scrollPane.setViewportView(list);
			}
		}
		{
			JLabel lblFirstName = new JLabel("First Name:");
			GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
			gbc_lblFirstName.anchor = GridBagConstraints.EAST;
			gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstName.gridx = 0;
			gbc_lblFirstName.gridy = 1;
			contentPanel.add(lblFirstName, gbc_lblFirstName);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JButton btnAdd = new JButton("Add");
			btnAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0)
				{
					try
					{
						Main.ll.createPerson(textField.getText(), textField_1.getText());
						textField.setText(null);
						textField_1.setText(null);		
						dialog.dispose();
						SelectPeopleWindow.openWindow();
					}
					catch(LogicLayerException e)
					{
						ExceptionWindow.openWindow(e.getMessage());
					}
				}
			});
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.fill = GridBagConstraints.VERTICAL;
			gbc_btnAdd.gridheight = 2;
			gbc_btnAdd.gridx = 2;
			gbc_btnAdd.gridy = 1;
			contentPanel.add(btnAdd, gbc_btnAdd);
		}
		{
			JLabel label = new JLabel("Last Name:");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			contentPanel.add(label, gbc_label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 0, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 2;
			contentPanel.add(textField_1, gbc_textField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						dialog.dispose();
						NewEventWindow.openWindow();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
