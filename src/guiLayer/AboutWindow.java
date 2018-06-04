package guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class responsible for an information about program view.
 */
@SuppressWarnings("serial")
public class AboutWindow extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private static AboutWindow dialog;
	
	/**
	 * Launch the application.
	 */
	public static void openWindow()
	{
		try
		{
			dialog = new AboutWindow();
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
	private AboutWindow()
	{
		addKeyListener(new KeyAdapter()
		{	@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE || e.getKeyCode()==KeyEvent.VK_ENTER)
				{	dialog.dispose();	}
			}
		});	
		
		setTitle("About");
		setBounds(100, 100, 249, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JTextPane textPane = new JTextPane();
		textPane.setText("Simple Java Calendar\r\n\r\n\r\nAuthors:\r\n\r\nJustyna Hubert\t210200\r\nKarol Podlewski\t210294");
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		contentPanel.add(textPane);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				dialog.dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

}
