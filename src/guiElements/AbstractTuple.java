package guiElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStructures.SuperkeyMap;

@SuppressWarnings("serial")
public abstract class AbstractTuple<A> extends JPanel implements ActionListener{
	protected Integer key;
	protected SuperkeyMap<A> map;
	protected JFrame parentFrame;
	protected JPanel parentPanel;
	
	public AbstractTuple(Integer key,SuperkeyMap<A> map,JFrame parentFrame,JPanel parentPanel) {
		super();
		this.map=map;
		this.key = key;
		this.parentFrame=parentFrame;
		this.parentPanel=parentPanel;
		initializeGUI();
	}
	
	public AbstractTuple(SuperkeyMap<A> map,JFrame parentFrame,JPanel parentPanel) {
		super();
		this.map=map;
		this.key = map.getUniqueKey();
		this.parentFrame=parentFrame;
		this.parentPanel=parentPanel;
		initializeGUI();
	}
	
	public abstract void initializeGUI();
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		parentPanel.remove(this);
		map.remove(key);
	}
}