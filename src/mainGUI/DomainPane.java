package mainGUI;

import guiElements.DomainTuple;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Attribute;
import dataStructures.AttributeMap;

@SuppressWarnings("serial")
public class DomainPane extends UpdatePane {

	private AttributeMap map;
	private JPanel domainPanel;
	private JFrame parentFrame;

	public DomainPane(AttributeMap oldMap, JFrame parentFrame) {
		this.map = oldMap;
		this.parentFrame = parentFrame;
		this.add(createGUI());
		setVisible(true);
	}

	private JPanel createGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		domainPanel = new JPanel();
		domainPanel.setLayout(new BoxLayout(domainPanel, BoxLayout.Y_AXIS));
		update();
		panel.add(domainPanel);
		return panel;
	}

	public void update() {
		// for every map entry, add a tuple to the table
		domainPanel.removeAll();
		JPanel label = new JPanel();
		label.setLayout(new BoxLayout(label, BoxLayout.X_AXIS));
		label.setPreferredSize(new Dimension(150,20));
		JTextField name = new JTextField("Attribute Name");
		JTextField domainEnum = new JTextField("Domain Enumeration");
		name.setEditable(false);
		domainEnum.setEditable(false);
		label.add(name);
		label.add(domainEnum);
		domainPanel.add(label);
		Collection<Entry<Integer, Attribute>> set = map.entrySet();
		for (Entry<Integer, Attribute> p : set){
			domainPanel.add(new DomainTuple(p.getKey(), map, parentFrame,
					domainPanel));
		}
		parentFrame.pack();
	}
}
