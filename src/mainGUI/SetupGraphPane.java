package mainGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import jungClasses.AnnotationControls;
import jungClasses.EditingModalGraphMouse;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.MapTransformer;
import org.apache.commons.collections15.map.LazyMap;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


@SuppressWarnings("serial")
public class SetupGraphPane extends UpdatePane{
	/**
     * the graph
     */
    private Graph<Number,Number> graph;
    
    private AbstractLayout<Number,Number> layout;

    /**
     * the visual component and renderer for the graph
     */
    private VisualizationViewer<Number,Number> vv;
	
	public SetupGraphPane(){
        
        // create a simple graph for the demo
        graph = new SparseMultigraph<Number,Number>();

        this.layout = new StaticLayout<Number,Number>(graph, 
        	new Dimension(600,600));
        
        vv =  new VisualizationViewer<Number,Number>(layout);
        vv.setBackground(Color.white);

        vv.getRenderContext().setVertexLabelTransformer(MapTransformer.<Number,String>getInstance(
        		LazyMap.<Number,String>decorate(new HashMap<Number,String>(), new ToStringLabeller<Number>())));
        
        vv.getRenderContext().setEdgeLabelTransformer(MapTransformer.<Number,String>getInstance(
        		LazyMap.<Number,String>decorate(new HashMap<Number,String>(), new ToStringLabeller<Number>())));

        vv.setVertexToolTipTransformer(vv.getRenderContext().getVertexLabelTransformer());
        

        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        add(panel);
        Factory<Number> vertexFactory = new VertexFactory();
        Factory<Number> edgeFactory = new EdgeFactory();
        
        final EditingModalGraphMouse<Number,Number> graphMouse = 
        	new EditingModalGraphMouse<Number,Number>(vv.getRenderContext(), vertexFactory, edgeFactory);
        
        // the EditingGraphMouse will pass mouse event coordinates to the
        // vertexLocations function to set the locations of the vertices as
        // they are created
//        graphMouse.setVertexLocations(vertexLocations);
        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());

        graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
        
        final ScalingControl scaler = new CrossoverScalingControl();
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1/1.1f, vv.getCenter());
            }
        });
        
//        JButton help = new JButton("Help");
//        help.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(vv, instructions);
//            }});

        AnnotationControls<Number,Number> annotationControls = 
        	new AnnotationControls<Number,Number>(graphMouse.getAnnotatingPlugin());
        JPanel controls = new JPanel();
        controls.add(plus);
        controls.add(minus);
        JComboBox modeBox = graphMouse.getModeComboBox();
        controls.add(modeBox);
        controls.add(annotationControls.getAnnotationsToolBar());
       // controls.add(help);
        add(controls, BorderLayout.SOUTH);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
    class VertexFactory implements Factory<Number> {

    	int i=0;

		public Number create() {
			return i++;
		}
    }
    
    
    class EdgeFactory implements Factory<Number> {

    	int i=0;
    	
		public Number create() {
			return i++;
		}
    }

}
