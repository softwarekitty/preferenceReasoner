package guiElements;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.Alternative;
import dataStructures.Attribute;
import dataStructures.AttributeKey;
import dataStructures.DomainValue;
import dataStructures.SuperkeyMap;
import dataStructures.ValueMap;

@SuppressWarnings("serial")
public class ValueTuple extends AbstractTuple<Alternative> implements
		ActionListener {

	private ArrayList<Attribute> allAttributes;
	private JComboBox[] boxes;

	public ValueTuple(Integer key, SuperkeyMap<Alternative> map,
			JFrame parentFrame, JPanel parentPanel,
			ArrayList<Attribute> allAttributes) {
		super(key, map, parentFrame, parentPanel);
		this.allAttributes = allAttributes;
		finishGUI();
	}

	/**
	 * Because the superclass calls intializeGUI before allAttributes list has
	 * been transferred, there is a finishGUI method that uses that information
	 * after it has become available.
	 */
	private void finishGUI() {
		// set up the boxes
		boxes = new JComboBox[allAttributes.size()];
		for (int i = 0; i < boxes.length; i++) {
			DomainValue[] values = allAttributes.get(i).getObject()
					.getDomainValueList().toArray();
			boxes[i] = new JComboBox(values);
			boxes[i].addActionListener(this);
			add(boxes[i]);
		}
		// then add a valuemap to the alternative with the defaults if there
		// isn't one yet
		Alternative a = map.get(key);
		ValueMap valueMap = a.getObject();
		if (valueMap == null) {
			ValueMap newMap = new ValueMap();
			for (JComboBox box : boxes) {
				DomainValue defaultValue = (DomainValue) box.getSelectedItem();
				newMap.put(defaultValue.getAttributeKey(), defaultValue);
			}
			a.setObject(newMap);

			// if there is already a valueMap, then set the boxes to reflect
			// what has already been selected
		} else {
			for (JComboBox box : boxes) {
				DomainValue defaultValue = (DomainValue) box.getSelectedItem();
				AttributeKey aKey = defaultValue.getAttributeKey();
				System.out.println("aKey: "+ aKey+" defaultValue: "+ defaultValue.toString());
				box.setSelectedItem(valueMap.get(aKey));
			}
		}
	}

	@Override
	public void initializeGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField alternativeName = new JTextField();
		alternativeName.setPreferredSize(new Dimension(75, 20));
		add(alternativeName);

		Alternative a = map.get(key);
		String name = "";
		if (a != null) {
			name = a.getName();
		}
			alternativeName.setText(name);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass() == JComboBox.class) {
			JComboBox activeComboBox = (JComboBox) e.getSource();
			for (JComboBox b : boxes) {
				if (activeComboBox == b) {
					System.out.println(b.getSelectedItem().getClass());
					DomainValue selectedValue = (DomainValue) b
							.getSelectedItem();
					Alternative a = map.get(key);
					
					System.out.println("Alternative: "+a.getName()+" selectedValue: "+selectedValue.toString()+" svKey: "+selectedValue.getAttributeKey());
					if (a == null) {
						System.err
								.println("In ValueTuple: Alternative should not be null!");
					}
					ValueMap valueMap = a.getObject();
					if (valueMap == null) {
						System.err
								.println("In ValueTuple: ValueMap should not be null!");
					} else
						valueMap.put(selectedValue.getAttributeKey(),
								selectedValue);
					break;
				}
			}
		}
	}
}
