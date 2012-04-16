package mainGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dataStructures.MetaData;
import dataStructures.ModelCheckerOption;

@SuppressWarnings("serial")
public class SetupProjectPane extends UpdatePane implements DocumentListener,
		ItemListener, ActionListener {

	// private JPanel inputPanel;
	private JFrame parentFrame;
	private MetaData metaData;
	private JTextField projectNameField;
	private JTextField filenameField;
	private JCheckBox sameNameCheckBox;
	private JComboBox modelCheckerComboBox;

	public SetupProjectPane(MetaData metaData, JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.metaData = metaData;
		this.add(createGUI());
		setVisible(true);
	}

	private JPanel createGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(new JLabel("Project Name"));
		projectNameField = new JTextField(35);
		projectNameField.getDocument().addDocumentListener(this);
		panel.add(projectNameField);

		JPanel filenameHeader = new JPanel();
		filenameHeader
				.setLayout(new BoxLayout(filenameHeader, BoxLayout.X_AXIS));
		filenameHeader.add(new JLabel("Filename"));
		sameNameCheckBox = new JCheckBox("same as project name");
		sameNameCheckBox.setSelected(true);
		sameNameCheckBox.addItemListener(this);
		filenameHeader.add(sameNameCheckBox);
		panel.add(filenameHeader);

		filenameField = new JTextField(35);
		filenameField.getDocument().addDocumentListener(this);
		filenameField.setEnabled(false);
		panel.add(filenameField);
		panel.add(new JLabel("Selected Model Checker"));

		ModelCheckerOption[] options = ModelCheckerOption.getAllOptions();
		modelCheckerComboBox = new JComboBox(options);
		modelCheckerComboBox.addActionListener(this);
		metaData.setSelectedModelChecker(options[0]);
		panel.add(modelCheckerComboBox);
		// update()
		// TODO-switch from Date to Callendar
		panel.add(new JLabel("Project created on: "
				+ metaData.getCreationDate().getMonth() + "/"
				+ metaData.getCreationDate().getDate() + "/"
				+ metaData.getCreationDate().getYear()));
		return panel;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getItemSelectable();
		if (o == sameNameCheckBox) {

			if (e.getStateChange() == ItemEvent.DESELECTED) {
				filenameField.setEnabled(true);
			} else {
				filenameField.setEnabled(false);
				filenameField.setText(projectNameField.getText());
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// not triggered by plain text
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		handleEvent(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleEvent(e);
	}

	private void handleEvent(DocumentEvent e) {
		if (e.getDocument() == projectNameField.getDocument()) {
			String projectName = projectNameField.getText();
			metaData.setProjectName(projectName);

			if (sameNameCheckBox.isSelected()) {
				filenameField.setText(projectName);
				metaData.setFilename(projectName);
			}

		} else if (e.getDocument() == filenameField.getDocument()) {
			if(!sameNameCheckBox.isSelected()){
				metaData.setFilename(filenameField.getText());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		ModelCheckerOption selectedOption = (ModelCheckerOption) cb
				.getSelectedItem();
		metaData.setSelectedModelChecker(selectedOption);
	}

}
