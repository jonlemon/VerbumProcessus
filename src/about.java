/*
 * Verbum Processus
 *
 * Copyright (C) 2014 Jon Bartlett
 *
 * Verbum Processus is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * Verbum Processus is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Verbum Processus; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class about extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			about dialog = new about();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public about() {
		setResizable(false);
		setTitle("About Verbum Processus");
		setBounds(100, 100, 406, 376);
		getContentPane().setLayout(new BorderLayout(5,5));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JLabel lblAboutHeader = new JLabel(" Verbum Processus");
			lblAboutHeader.setFont(new Font("Calibri", Font.PLAIN, 35));
			lblAboutHeader.setIcon(new ImageIcon(about.class.getResource("/resources/rjblogo.png")));
			lblAboutHeader.setBorder(new EmptyBorder(10,10,10,10));
			getContentPane().add(lblAboutHeader, BorderLayout.NORTH);
		}
		{
			JTextArea txtAboutBody = new JTextArea();
			txtAboutBody.setWrapStyleWord(true);
			txtAboutBody.setFont(new Font("Calibri Light", Font.PLAIN, 13));
			txtAboutBody.setLineWrap(true);
			txtAboutBody.setText("Copyright (C) 2014 Jon Bartlett \n \n" +
					"This program is free software: you can redistribute it and/or modify " +
					"it under the terms of the GNU General Public License as published by " +
					"the Free Software Foundation, either version 3 of the License, or " +
					"(at your option) any later version.\n \n" +
					"This program is distributed in the hope that it will be useful, " +
					"but WITHOUT ANY WARRANTY; without even the implied warranty of " +
					"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the " +
					"GNU General Public License for more details.");
			txtAboutBody.setBackground(SystemColor.control);
			txtAboutBody.setMargin(new Insets(10,10,10,10));
			getContentPane().add(txtAboutBody, BorderLayout.CENTER);
		}
	}

}
