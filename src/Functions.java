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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;


public class Functions {
	
	public static boolean fileOpened = false;
	public static File currentFile;
	public static String copyText = null;
	
	public Functions () {
		
	}

	
	public static BufferedReader getTextFromFile() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
			  File file = fc.getSelectedFile();
			  updateCurrentFile(file);
			  FileReader fileReader = new FileReader(file);
			  BufferedReader buffReader = new BufferedReader(fileReader);
			  return buffReader;
			}
			catch(FileNotFoundException e1) {
			
			} 
		}
		return null;
	}
	
	public static void saveFile (JTextArea jta)  {
		if(fileOpened == false) {
			saveFileAs(jta);
		} else {
			BufferedWriter outFile = null;
			try {
				System.out.print(fileOpened);
				outFile = new BufferedWriter(new FileWriter(currentFile));
				jta.write(outFile);
				outFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void saveFileAs(JTextArea jta) {
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			BufferedWriter outFile = null;
			try {
				File file = fc.getSelectedFile();
				updateCurrentFile(file);
				if(!fc.getSelectedFile().getAbsolutePath().endsWith(".txt")){
					file = new File(fc.getSelectedFile() + ".txt");
				}
				outFile = new BufferedWriter(new FileWriter(file));
				jta.write(outFile);
			} catch (IOException ex) {
		         ex.printStackTrace();
		    } finally {
		        if (outFile != null) {
		            try {
		            	outFile.close();
		            } catch (IOException e) {
		            }
		        }
		    }
		}
		
		
	}
	
	public static void updateCurrentFile (File active) {
		currentFile = active;
		fileOpened = true;
	}
	
	public static void clearCurrentFile() {
		//Do you want to save current file?
		currentFile = null;
		fileOpened = false;
		
	}
	
	public static void storeText(String text) {
		copyText = text;
	}
	
	public static String recallText() {
		return copyText;
	}
	
	public static boolean savePrompt() {
		Save savePrompt = new Save();
		savePrompt.setModal(true);
		savePrompt.setLocationRelativeTo(null);
		savePrompt.setVisible(true);
		while (savePrompt.isVisible() == true) {
			
		}
		boolean tempAccept = savePrompt.Accepted();
		savePrompt.dispose();
		return tempAccept;
	}
	
}
