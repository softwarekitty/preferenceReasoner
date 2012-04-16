package mainGUI;

import guiElements.SelectableTextPane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import dataStructures.Document;

@SuppressWarnings("serial")
public class PaneTurner extends JSplitPane implements ActionListener {

	private JFrame parent;
	private JButton nextButton;
	private JButton prevButton;

	private SelectableTextPane[] metaPanes;
	private UpdatePane[] viewPanes;
	private int currentSelected;
	private Document document;

	//TODO - make currentSelected come from Document MetaData
	public PaneTurner(JFrame parent, Document document) {
		this.parent = parent;
		this.document = document;
		this.setLeftComponent(getChooser());
		this.setRightComponent(intitializeViewPanes());
	}

	private Component intitializeViewPanes() {
		viewPanes = new UpdatePane[metaPanes.length];
		viewPanes[0] = new SetupProjectPane(document.getMetaData(),parent);
		viewPanes[1] = new AttributePane(document.getAttributeMap(), parent);
		viewPanes[2] = new DomainPane(document.getAttributeMap(), parent);
		viewPanes[3] = new AlternativePane(document.getAlternativeMap(), parent);
		viewPanes[4] = new ValuePane(document.getAlternativeMap(),document.getAttributeMap(), parent);
		viewPanes[5] = new SetupGraphPane();
		viewPanes[6] = new ViewResultsPane();
		return viewPanes[currentSelected];
	}

	private JPanel getChooser() {
		JPanel chooser = new JPanel();
		chooser.setLayout(new BoxLayout(chooser, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		nextButton = new JButton("Next");
		prevButton = new JButton("Prev");
		nextButton.addActionListener(this);
		prevButton.addActionListener(this);
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		chooser.add(buttonPanel);
		String[] steps = { "Setup Project", "Add Attributes", "Attribute \nDomains",
				"Add \nAlternatives", "Alternative \nValues","Setup Graph", "View Result" };
		metaPanes = new SelectableTextPane[steps.length];
		for (int i = 0; i < steps.length; i++) {
			metaPanes[i] = new SelectableTextPane(steps[i]);
			chooser.add(metaPanes[i]);
		}
		currentSelected = 0;
		metaPanes[currentSelected].toggleColor();
		return chooser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextButton) {
			metaPanes[currentSelected].toggleColor();
			if (currentSelected < metaPanes.length - 1) {
				currentSelected++;
				viewPanes[currentSelected].update();
				this.setRightComponent(viewPanes[currentSelected]);
			}
			metaPanes[currentSelected].toggleColor();
			// go to next step
		} else if (e.getSource() == prevButton) {
			metaPanes[currentSelected].toggleColor();
			if (currentSelected > 0) {
				currentSelected--;
				viewPanes[currentSelected].update();
				this.setRightComponent(viewPanes[currentSelected]);
			}
			metaPanes[currentSelected].toggleColor();
			// go to previous step if possible
		}

	}

	public void pack() {
		parent.pack();
	}

}
