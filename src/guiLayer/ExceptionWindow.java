package guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ExceptionWindow extends JDialog
{
	private static ExceptionWindow dialog;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void OpenWindow(String msg)
	{
		try
		{
			dialog = new ExceptionWindow(msg);
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
	public ExceptionWindow(String msg)
	{
		setTitle("Error");
		setBounds(100, 100, 300, 111);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{265, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel(msg);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{300, 0};
			gbl_buttonPane.rowHeights = new int[]{25, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_ESCAPE){
							dialog.dispose();
						}
					}
				});
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.NORTH;
				gbc_okButton.gridx = 0;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
