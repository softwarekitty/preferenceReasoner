package mainGUI;

import guiElements.AlternativeTuple;
import guiElements.AttributeTuple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Alternative;
import dataStructures.AlternativeMap;
import dataStructures.Attribute;
import dataStructures.AttributeMap;


@SuppressWarnings("serial")
public class AlternativePane extends UpdatePane implements ActionListener{
private AlternativeMap map;
private JPanel alternativePanel;
private JFrame parentFrame;
private JButton plusButton;
	
	public AlternativePane(AlternativeMap alternativeMap,JFrame parent) {
		this.parentFrame=parent;
		this.map=alternativeMap;
		this.add(initializeGUI());
	}
	
	private JPanel initializeGUI(){
		JPanel panel= new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		alternativePanel = new JPanel();
		alternativePanel.setLayout(new BoxLayout(alternativePanel, BoxLayout.Y_AXIS));
		update();
		panel.add(alternativePanel);
		plusButton = new JButton("+");
		plusButton.addActionListener(this);
		panel.add(plusButton);
		return panel;
	}

	@Override
	public void update() {
		alternativePanel.removeAll();
		
		JTextField columnName = new JTextField("Alternative");
		columnName.setEditable(false);
		alternativePanel.add(columnName);
		
		
		//for every map entry, add a tuple to the table,then one more
		Collection<Entry<Integer, Alternative>> set = map
				.entrySet();
		for (Entry<Integer, Alternative> p : set)
			alternativePanel.add(new AlternativeTuple(p.getKey(),map,parentFrame,alternativePanel));
		alternativePanel.add(new AlternativeTuple(map,parentFrame,alternativePanel));
		parentFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (plusButton == e.getSource()) {
			alternativePanel.add(new AlternativeTuple(map,parentFrame,alternativePanel));
			pack();
		}
	}
	
	public void pack(){
		parentFrame.pack();
	}

}
