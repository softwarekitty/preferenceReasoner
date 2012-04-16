package guiElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dataStructures.Condition;
import dataStructures.Pair;
import dataStructures.Preference;
import dataStructures.Statement;
import dataStructures.StatementMap;

@SuppressWarnings("serial")
public class EditPreferenceDialog extends JDialog implements ActionListener {
	private EditPreferenceDialog self;
	private JPanel tablePanel;
	private JButton plusButton;
	private JButton okButton;
	private StatementMap map;

	public StatementMap getStatementMap() {
		return map;
	}

	public EditPreferenceDialog(JFrame frame, StatementMap oldMap) {
		super(frame, true);
		self = this;
		if (oldMap == null)
			oldMap = new StatementMap();
		this.map = oldMap;
		getContentPane().add(createGUI());
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	private JPanel createGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		JPanel tableLabel = new JPanel();
		tableLabel.setLayout(new BoxLayout(tableLabel, BoxLayout.X_AXIS));
		JLabel left = new JLabel("Condition  ");
		JLabel right = new JLabel("Preference                   ");
		tableLabel.add(left);
		tableLabel.add(right);
		tablePanel.add(tableLabel);
		Collection<Entry<Integer, Statement>> set = map
				.entrySet();
		for (Entry<Integer, Statement> p : set)
			tablePanel.add(new XTuple(p.getKey()));
		tablePanel.add(new XTuple());
		plusButton = new JButton("+");
		plusButton.addActionListener(this);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		panel.add(tablePanel);
		panel.add(plusButton);
		panel.add(okButton);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (plusButton == e.getSource()) {
			tablePanel.add(new XTuple());
			this.pack();
		} else if (okButton == e.getSource()) {
			map.clearUnusedKeys();
			dispose();
		}
	}

	class XTuple extends JPanel implements ActionListener {

		Integer key;
		JTextField left;
		JTextField right;
		JButton xButton;

		public XTuple(Integer key) {
			this();
			this.key = key;
			Pair<Condition, Preference> pair = map.get(key);
			setTupleText(pair.getLeft().toString(), pair.getRight().toString());
		}

		public XTuple() {
			super();
			this.key = map.getUniqueKey();
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			left = new JTextField(4);
			left.getDocument().addDocumentListener(new FieldListener(left));
			this.add(left);
			right = new JTextField(4);
			right.getDocument().addDocumentListener(new FieldListener(right));
			this.add(right);
			xButton = new JButton("x");
			xButton.addActionListener(this);
			this.add(xButton);
		}

		public void setTupleText(String conditionString, String preferenceString) {
			this.left.setText(conditionString);
			this.right.setText(preferenceString);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			tablePanel.remove(this);
			map.remove(key);
			self.pack();
		}

		class FieldListener implements DocumentListener {
			JTextField side;

			public FieldListener(JTextField side) {
				this.side = side;
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// not triggered by plain text
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				handleChange(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				handleChange(e);
				
			}
			
			private void handleChange(DocumentEvent e){
				boolean newEntry = false;
				Statement statement = map.get(key);
				if (statement == null) {
					newEntry = true;
					statement = new Statement(new Condition(""),
							new Preference(""));
				}
				if (side == left) {
					statement.setLeft(new Condition(left.getText()));
					if (newEntry)
						map.put(key, statement);
					self.pack();
				} else if (side == right) {
					statement.setRight(new Preference(right.getText()));
					if (newEntry)
						map.put(key, statement);
					self.pack();
				}
			}

		}

	}

}
