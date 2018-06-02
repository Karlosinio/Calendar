package guiLayer;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import logicLayer.BinarySerializer;
import logicLayer.Serializer;
import logicLayer.XMLSerializer;


@SuppressWarnings("serial")
public class SettingsWindow extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private static SettingsWindow dialog;
	
	private static JComboBox<Serializer> comboBox;
	
	private static String fileName;
	private static Serializer serializer;
	
	private static TextField tfLocation;
	
	/**
	 * Launch the application.
	 */
	public static void openWindow()
	{
		try
		{
			fileName = Main.ll.getFileName();
			serializer = Main.ll.getSerializer();
			
			dialog = new SettingsWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	private SettingsWindow()
	{
		setTitle("Settings");
		setBounds(100, 100, 400, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnResetToDefault = new JButton("Reset to default settings");
		btnResetToDefault.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				comboBox.setSelectedIndex(0);
				tfLocation.setText(fileName + serializer.getFileFormat());
			}
		});

		btnResetToDefault.setBounds(12, 13, 358, 25);
		contentPanel.add(btnResetToDefault);
				
		Label lblFileName = new Label("File name:");
		lblFileName.setBounds(12, 44, 60, 24);
		contentPanel.add(lblFileName);
		
		tfLocation = new TextField(fileName + serializer.getFileFormat());
		tfLocation.setBounds(78, 44, 294, 24);
		contentPanel.add(tfLocation);
		
		/*
		Button btnSelectLocationbutton = new Button("Select location");
		btnSelectLocationbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				JFileChooser fs = new JFileChooser(new File(".\\autosave.xml"));
				fs.setDialogTitle("Select file");
				fs.showSaveDialog(null);
			}
		});
		btnSelectLocationbutton.setBounds(242, 72, 128, 24);
		contentPanel.add(btnSelectLocationbutton);
		*/
		
		Label lblFormat = new Label("Default format:");
		lblFormat.setBounds(12, 72, 90, 24);
		contentPanel.add(lblFormat);
	
		comboBox = new JComboBox<Serializer>();
		comboBox.setBounds(108, 72, 98, 24);
		comboBox.addItem(new XMLSerializer());
		comboBox.addItem(new BinarySerializer());
		contentPanel.add(comboBox);
		comboBox.addItemListener(new ItemListener()
		{
	        public void itemStateChanged(ItemEvent arg0)
	        {
	        	fileName = tfLocation.getText().split("\\.")[0];
				serializer = (Serializer) comboBox.getSelectedItem();
				tfLocation.setText(fileName + serializer.getFileFormat());
	        }
	    });
		
		if(serializer instanceof BinarySerializer)
			comboBox.setSelectedIndex(1);
	
		/////////////////////////////////////////////////////
		// Ok / Cancel Pane
		/////////////////////////////////////////////////////
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				Main.ll.setFileName(tfLocation.getText().split("\\.")[0]);
				Main.ll.setSerializer((Serializer) comboBox.getSelectedItem());
				dialog.dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				dialog.dispose();
			}
		});
		buttonPane.add(cancelButton);
	}
}
