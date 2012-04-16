package mainGUI;

import guiElements.ValueTuple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Alternative;
import dataStructures.AlternativeMap;
import dataStructures.Attribute;
import dataStructures.AttributeMap;

@SuppressWarnings("serial")
public class ValuePane extends UpdatePane implements ActionListener{
	private AlternativeMap alternativeMap;
	private AttributeMap attributeMap;
	private JPanel valuePanel;
	private JFrame parentFrame;
		
		public ValuePane(AlternativeMap alternativeMap, AttributeMap attributeMap,JFrame parent) {
			this.parentFrame=parent;
			this.alternativeMap=alternativeMap;
			this.attributeMap=attributeMap;
			this.add(initializeGUI());
		}
		
		private JPanel initializeGUI(){
			JPanel panel= new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			valuePanel = new JPanel();
			valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
			update();
			panel.add(valuePanel);
			return panel;
		}

		@Override
		public void update() {
			valuePanel.removeAll();
			
			//put column names at top of valuePanel
			ArrayList<Attribute> allAttributes = new ArrayList<Attribute>(attributeMap.values());
			Collections.sort(allAttributes);
			
			JPanel columnTitles = new JPanel();
			columnTitles.setLayout(new BoxLayout(columnTitles, BoxLayout.X_AXIS));
			
			JTextField origin = new JTextField("Alternative");
			origin.setEditable(false);
			columnTitles.add(origin);
			
			for(Attribute att : allAttributes){
				JTextField attName = new JTextField(att.getName());
				attName.setEditable(false);
				columnTitles.add(attName);
			}
			valuePanel.add(columnTitles);
			
			//make a tuple for every attribute
			LinkedList<Alternative> allAlternatives = new LinkedList<Alternative>(alternativeMap.values());
			Collections.sort(allAlternatives);
			
			for(Alternative a : allAlternatives){
				valuePanel.add(new ValueTuple(a.getKey(),alternativeMap,parentFrame,this,allAttributes));
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

}
