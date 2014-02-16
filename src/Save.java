import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Save extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static boolean accept = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Save dialog = new Save();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Save() {
		setResizable(false);
		setBounds(100, 100, 260, 122);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextArea txtrThisSiThe = new JTextArea();
			txtrThisSiThe.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtrThisSiThe.setText("Do you want to save changes?");
			txtrThisSiThe.setBackground(SystemColor.control);
			contentPanel.add(txtrThisSiThe);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						accept = true;
					}
				});
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton btnDontSave = new JButton("Don't Save");
				btnDontSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						accept = true;
					}
				});
				buttonPane.add(btnDontSave);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						accept = false;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean Accepted() {
		return accept;
	}

}
