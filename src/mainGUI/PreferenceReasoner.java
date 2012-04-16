package mainGUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import dataStructures.Document;

@SuppressWarnings("serial")
public class PreferenceReasoner extends JApplet {
	private static PaneTurner paneTurner;
	private static Document document;
	

	public static void main(String[] args) {
		// new JDialog: name your project
		final JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(900,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paneTurner=null;
		//document =new Document();;
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new AbstractAction("Save") {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(paneTurner);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					save();
				}
			}
		});
		fileMenu.add(new AbstractAction("New") {
			public void actionPerformed(ActionEvent e) {
				if(paneTurner!=null){
					//offer to save old document before opening new one
				}
				document = new Document();
				paneTurner = new PaneTurner(frame,document);
				frame.getContentPane().add(paneTurner);
				frame.pack();
			}
		});
		fileMenu.add(new AbstractAction("Open") {
			public void actionPerformed(ActionEvent e) {
				//offer to save old document before opening new one
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(paneTurner);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					open();
				}
			}
		});
		
		
		// JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		frame.pack();
	}
	
	private static void save(){
		//...
	}
	
	private static void open(){
		//...
	}
}
