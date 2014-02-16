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

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JScrollPane;

import java.awt.Toolkit;

import javax.swing.KeyStroke;

import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.JPanel;


public class window {

	private JFrame frmVerbumProcessus;
	final JFileChooser fc = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) { }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frmVerbumProcessus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerbumProcessus = new JFrame();
		frmVerbumProcessus.setIconImage(Toolkit.getDefaultToolkit().getImage(window.class.getResource("/resources/rjblogo.png")));
		frmVerbumProcessus.setTitle("Verbum Processus");
		frmVerbumProcessus.setBounds(100, 100, 613, 460);
		frmVerbumProcessus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CaretListenerLabel caretListenerLabel = new CaretListenerLabel();
		final JTextArea textArea = new JTextArea();

		
		
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		frmVerbumProcessus.getContentPane().add(scrollPane, BorderLayout.CENTER);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		final JPanel panel = new JPanel();
		frmVerbumProcessus.getContentPane().add(panel, BorderLayout.SOUTH);
		String buffer = "                                    ";
		final JLabel lblStatus = new JLabel("Line: 1, Col: 1");
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblStatus);
		panel.setVisible(false);
		Dimension d = lblStatus.getPreferredSize();  
	    lblStatus.setPreferredSize(new Dimension(d.width+120,d.height));
		
		textArea.addCaretListener(new CaretListener ()	{	
			public void caretUpdate(CaretEvent e) {
				   int caretpos = textArea.getCaretPosition();
				   int row;
				   int column;
				try {
					row = (textArea.getLineOfOffset(caretpos)) + 1;
					column = caretpos - textArea.getLineStartOffset(row - 1) + 1;
					lblStatus.setText("Line: " + row + ", Col: " + column);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
			}
		});
		

		JMenuBar menuBar = new JMenuBar();
		frmVerbumProcessus.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//###Prompt to save current text
				Functions.clearCurrentFile();
				textArea.setText(null);
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader tempReader = Functions.getTextFromFile();
					if(tempReader != null) {
						textArea.read(tempReader, textArea);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions.saveFile(textArea);
			}
		});
		mntmSave.setMnemonic(KeyEvent.VK_N);
		mnFile.add(mntmSave);
		
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions.saveFileAs(textArea);
			}
		});
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
				//Functions.storeText(textArea.getSelectedText());
			}
		});
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmStatusBar = new JMenuItem("Status Bar");
		mntmStatusBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(!panel.isVisible());
			}
		});
		mnView.add(mntmStatusBar);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnNewMenu.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about info = new about();
				info.setLocationRelativeTo(null);
				info.setVisible(true);
			}
		});
		mntmAbout.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				
			}
		});
		
		
	}
	
	protected class CaretListenerLabel extends JLabel
    implements CaretListener
	{


		@Override
		public void caretUpdate(CaretEvent e) {
			//System.out.print(e.getDot() + " - " + e.getMark());
			//textArea
			
		}
	}

}
