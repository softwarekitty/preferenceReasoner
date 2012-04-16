package graph;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import jungClasses.AnnotatingGraphMousePlugin;
import jungClasses.Annotation;
import dataStructures.StatementMap;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import guiElements.EditPreferenceDialog;

public class PreferenceMousePlugin<V, E> extends
		AnnotatingGraphMousePlugin<V, E> {

	/**
	 * used to draw a rectangle to contain picked vertices
	 */
	protected Rectangle2D rect = new Rectangle2D.Float();

	/**
	 * the picked Vertex, if any
	 */
	protected V vertex;

	/**
	 * the picked Edge, if any
	 */
	protected E edge;

	/**
	 * the x distance from the picked vertex center to the mouse point
	 */
	protected double offsetx;

	/**
	 * the y distance from the picked vertex center to the mouse point
	 */
	protected double offsety;

	public PreferenceMousePlugin(RenderContext<V, E> rc) {
		super(rc);

	}

	/**
	 * selects a single vertex, or if one is selected and pop up button is
	 * pressed, opens EditPreferenceDialog to annotate that vertex
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void mousePressed(MouseEvent e) {
		//get the tools to discern the vertex
		down = e.getPoint();
		@SuppressWarnings("rawtypes")
		VisualizationViewer<V, E> vv = (VisualizationViewer) e.getSource();
		GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
		PickedState<V> pickedVertexState = vv.getPickedVertexState();
		
		//if these tools are not null, get the vertex selected
		if (pickSupport != null && pickedVertexState != null && vv != null) {
			Layout<V, E> layout = vv.getGraphLayout();
			Point2D ip = e.getPoint();
			vertex = pickSupport.getVertex(layout, ip.getX(), ip.getY());
			if(vertex != null) {
                if(pickedVertexState.isPicked(vertex) == false) {
                	pickedVertexState.clear();
                	pickedVertexState.pick(vertex, true);
                }else if (e.isPopupTrigger()){
            		Annotation<StatementMap,V> thisAnnotation = annotationManager.getAnnotation(vertex);
            		StatementMap oldMap = null;
            		if(thisAnnotation!=null){
            			oldMap = (StatementMap)thisAnnotation.getAnnotation();
            			annotationManager.remove(thisAnnotation);
            		}else
            			thisAnnotation = new Annotation<StatementMap,V>(null,layer,annotationColor,fill,ip,vertex);
            		
            		EditPreferenceDialog editPreferenceDialog =  new EditPreferenceDialog(null, oldMap);
            		StatementMap newTable = editPreferenceDialog.getStatementMap();
            		thisAnnotation.setAnnotation(newTable);
            		annotationManager.add(layer, thisAnnotation);
                }
            }else {
                vv.addPostRenderPaintable(lensPaintable);
                pickedVertexState.clear();
            }

		}
	}

	public void mouseReleased(MouseEvent e) {
		// do nothing
	}

	public void mouseDragged(MouseEvent e) {
		// do nothing
	}

}
