package guiElements;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import dataStructures.Attribute;
import dataStructures.AttributeKey;
import dataStructures.AttributeMap;

@SuppressWarnings("serial")
public class AttributeTuple extends AbstractTuple<Attribute> implements
		ActionListener {

	protected JTextField attributeName;
	protected JButton xButton;

	public AttributeTuple(Integer key, AttributeMap map, JFrame parent,
			JPanel parentPanel) {
		super(key, map, parent, parentPanel);
	}

	public AttributeTuple(AttributeMap map, JFrame parent, JPanel parentPanel) {
		super(map, parent, parentPanel);
	}

	@Override
	public void initializeGUI() {
		attributeName = new JTextField(20);
		Attribute a = map.get(key);
		if (a != null)
			attributeName.setText(a.getName());
		attributeName.getDocument().addDocumentListener(
				new AttributeTextListener(attributeName));
		this.add(attributeName);
		xButton = new JButton("x");
		xButton.addActionListener(this);
		this.add(xButton);
	}

	class AttributeTextListener extends AbstractTextListener {

		public AttributeTextListener(JTextField field) {
			super(field);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			handleChange();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			handleChange();
		}

		private void handleChange() {
			boolean newEntry = false;
			Attribute a = map.get(key);
			if (a == null) {
				newEntry = true;
				a = new Attribute("", new AttributeKey(key), null);
			}
			if (field == attributeName) {
				a.setName(attributeName.getText());
				if (newEntry)
					map.put(key, a);
				parentFrame.pack();
			}
		}

	}

}
