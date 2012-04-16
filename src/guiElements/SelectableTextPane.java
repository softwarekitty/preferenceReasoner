package guiElements;

import java.awt.Color;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectableTextPane extends JPanel {
	private JEditorPane editorPane;
	private boolean isSelected;

	public SelectableTextPane(String message) {
		this.editorPane = new JEditorPane();
		editorPane.setContentType("text/plain");
		editorPane.setText(message);
		editorPane.setEditable(false);
		this.add(editorPane);
		isSelected = false;
		this.setBackground(Color.GRAY);
	}

	public void toggleColor() {
		if (isSelected) {
			this.setBackground(Color.GRAY);
		} else
			this.setBackground(Color.BLUE);
		isSelected = !isSelected;
	}

}
