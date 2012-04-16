package guiElements;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import dataStructures.Alternative;
import dataStructures.AlternativeMap;

@SuppressWarnings("serial")
public class AlternativeTuple extends AbstractTuple<Alternative> implements
		ActionListener {
	protected JTextField alternativeName;
	protected JButton xButton;

	public AlternativeTuple(Integer key, AlternativeMap map, JFrame parent,
			JPanel parentPanel) {
		super(key, map, parent, parentPanel);
	}

	public AlternativeTuple(AlternativeMap map, JFrame parent, JPanel parentPanel) {
		super(map, parent, parentPanel);
	}

	@Override
	public void initializeGUI() {
		alternativeName = new JTextField(20);
		Alternative a = map.get(key);
		if (a != null)
			alternativeName.setText(a.getName());
		alternativeName.getDocument().addDocumentListener(
				new AlternativeTextListener(alternativeName));
		this.add(alternativeName);
		xButton = new JButton("x");
		xButton.addActionListener(this);
		this.add(xButton);
	}

	class AlternativeTextListener extends AbstractTextListener {

		public AlternativeTextListener(JTextField field) {
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
			Alternative a = map.get(key);
			if (a == null) {
				newEntry = true;
				a = new Alternative("", key, null);
			}
			if (field == alternativeName) {
				a.setName(alternativeName.getText());
				if (newEntry)
					map.put(key, a);
				parentFrame.pack();
			}
		}

	}
}
