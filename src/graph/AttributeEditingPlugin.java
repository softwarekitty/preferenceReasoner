package graph;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

import org.apache.commons.collections15.Factory;

import dataStructures.Attribute;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import jungClasses.EditingGraphMousePlugin;

public class AttributeEditingPlugin<V, E> extends EditingGraphMousePlugin<V, E> {
	private Attribute attributeToCreate;

	public AttributeEditingPlugin(Factory<V> vertexFactory,
			Factory<E> edgeFactory) {
		super(vertexFactory, edgeFactory);
	}
    /**
     * Overridden to be more flexible, and pass events with
     * key combinations. The default responds to both ButtonOne
     * and ButtonOne+Shift
     */
    @Override
    public boolean checkModifiers(MouseEvent e) {
        return (e.getModifiers() & modifiers) != 0;
    }

    /**
     * If the mouse is pressed in an empty area, create a new vertex there.
     * If the mouse is pressed on an existing vertex, prepare to create
     * an edge from that vertex to another
     */
    @SuppressWarnings("unchecked")
    @Override
	public void mousePressed(MouseEvent e) {
    	System.out.println("here1");
//        if(checkModifiers(e)) {
//            final VisualizationViewer<V,E> vv =
//                (VisualizationViewer<V,E>)e.getSource();
//            final Point2D p = e.getPoint();
//            GraphElementAccessor<V,E> pickSupport = vv.getPickSupport();
//            if(pickSupport != null) {
//            	Graph<V,E> graph = vv.getModel().getGraphLayout().getGraph();
//            	// set default edge type
//            	if(graph instanceof DirectedGraph) {
//            		edgeIsDirected = EdgeType.DIRECTED;
//            	} else {
//            		edgeIsDirected = EdgeType.UNDIRECTED;
//            	}
//            	
//                final V vertex = pickSupport.getVertex(vv.getModel().getGraphLayout(), p.getX(), p.getY());
//                if(vertex != null) { // get ready to make an edge
//                	System.out.println("here6");
////                    startVertex = vertex;
////                    down = e.getPoint();
////                    transformEdgeShape(down, down);
////                    vv.addPostRenderPaintable(edgePaintable);
////                    if((e.getModifiers() & MouseEvent.SHIFT_MASK) != 0
////                    		&& vv.getModel().getGraphLayout().getGraph() instanceof UndirectedGraph == false) {
////                        edgeIsDirected = EdgeType.DIRECTED;
////                    }
////                    if(edgeIsDirected == EdgeType.DIRECTED) {
////                        transformArrowShape(down, e.getPoint());
////                        vv.addPostRenderPaintable(arrowPaintable);
////                    }
//                } else { // make a new vertex
//                	System.out.println("here2");
//                    V newVertex = vertexFactory.create();
//                    Layout<V,E> layout = vv.getModel().getGraphLayout();
//                    graph.addVertex(newVertex);
//                    layout.setLocation(newVertex, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint()));
//                }
//            }
//            vv.repaint();
//        }
    }
    
    /**
     * If startVertex is non-null, and the mouse is released over an
     * existing vertex, create an undirected edge from startVertex to
     * the vertex under the mouse pointer. If shift was also pressed,
     * create a directed edge instead.
     */
    @SuppressWarnings("unchecked")
    @Override
	public void mouseReleased(MouseEvent e) {
    	System.out.println("here5");
//        if(checkModifiers(e)) {
//        	System.out.println("here3");
//            final VisualizationViewer<V,E> vv =
//                (VisualizationViewer<V,E>)e.getSource();
//            final Point2D p = e.getPoint();
//            Layout<V,E> layout = vv.getModel().getGraphLayout();
//            GraphElementAccessor<V,E> pickSupport = vv.getPickSupport();
//            if(pickSupport != null) {
//                final V vertex = pickSupport.getVertex(layout, p.getX(), p.getY());
//                if(vertex != null && startVertex != null) {
//                	System.out.println("here4");
//                    Graph<V,E> graph = 
//                    	vv.getGraphLayout().getGraph();
//                        graph.addEdge(edgeFactory.create(),
//                        		startVertex, vertex, edgeIsDirected);
//                    vv.repaint();
//                }
//            }
//            startVertex = null;
//            down = null;
//            edgeIsDirected = EdgeType.UNDIRECTED;
//            vv.removePostRenderPaintable(edgePaintable);
//            vv.removePostRenderPaintable(arrowPaintable);
//        }
    }

    /**
     * If startVertex is non-null, stretch an edge shape between
     * startVertex and the mouse pointer to simulate edge creation
     */
    @SuppressWarnings("unchecked")
    @Override
    public void mouseDragged(MouseEvent e) {
    	System.out.println("here7");
        if(checkModifiers(e)) {
            if(startVertex != null) {
                transformEdgeShape(down, e.getPoint());
                if(edgeIsDirected == EdgeType.DIRECTED) {
                    transformArrowShape(down, e.getPoint());
                }
            }
            VisualizationViewer<V,E> vv =
                (VisualizationViewer<V,E>)e.getSource();
            vv.repaint();
        }
    }
    
    /**
     * code lifted from PluggableRenderer to move an edge shape into an
     * arbitrary position
     */
    private void transformEdgeShape(Point2D down, Point2D out) {
    	System.out.println("here9");
        float x1 = (float) down.getX();
        float y1 = (float) down.getY();
        float x2 = (float) out.getX();
        float y2 = (float) out.getY();

        AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
        
        float dx = x2-x1;
        float dy = y2-y1;
        float thetaRadians = (float) Math.atan2(dy, dx);
        xform.rotate(thetaRadians);
        float dist = (float) Math.sqrt(dx*dx + dy*dy);
        xform.scale(dist / rawEdge.getBounds().getWidth(), 1.0);
        edgeShape = xform.createTransformedShape(rawEdge);
    }
    
    
    private void transformArrowShape(Point2D down, Point2D out) {
    	System.out.println("here8");
        float x1 = (float) down.getX();
        float y1 = (float) down.getY();
        float x2 = (float) out.getX();
        float y2 = (float) out.getY();

        AffineTransform xform = AffineTransform.getTranslateInstance(x2, y2);
        
        float dx = x2-x1;
        float dy = y2-y1;
        float thetaRadians = (float) Math.atan2(dy, dx);
        xform.rotate(thetaRadians);
        arrowShape = xform.createTransformedShape(rawArrowShape);
    }
    
    /**
     * Used for the edge creation visual effect during mouse drag
     */
    class EdgePaintable implements VisualizationServer.Paintable {
        
        public void paint(Graphics g) {
            if(edgeShape != null) {
            	System.out.println("here11");
                Color oldColor = g.getColor();
                g.setColor(Color.black);
                ((Graphics2D)g).draw(edgeShape);
                g.setColor(oldColor);
            }
        }
        
        public boolean useTransform() {
            return false;
        }
    }
    
    /**
     * Used for the directed edge creation visual effect during mouse drag
     */
    class ArrowPaintable implements VisualizationServer.Paintable {
        
        public void paint(Graphics g) {
            if(arrowShape != null) {
            	System.out.println("here22");
                Color oldColor = g.getColor();
                g.setColor(Color.black);
                ((Graphics2D)g).fill(arrowShape);
                g.setColor(oldColor);
            }
        }
        
        public boolean useTransform() {
            return false;
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent)e.getSource();
        c.setCursor(cursor);
    }
    public void mouseExited(MouseEvent e) {
        JComponent c = (JComponent)e.getSource();
        c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    public void mouseMoved(MouseEvent e) {}
	

	/**
	 * If startVertex is non-null, and the mouse is released over an existing
	 * vertex, create an undirected edge from startVertex to the vertex under
	 * the mouse pointer. If shift was also pressed, create a directed edge
	 * instead.
	 */
//	@SuppressWarnings("unchecked")
//	public void mouseReleased(MouseEvent e) {
//		if (checkModifiers(e)) {
//			final VisualizationViewer<V, E> vv = (VisualizationViewer<V, E>) e
//					.getSource();
//			final Point2D p = e.getPoint();
//			Layout<V, E> layout = vv.getModel().getGraphLayout();
//			GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
//			if (pickSupport != null) {
//
//				
//				final V vertex = pickSupport.getVertex(layout, p.getX(),
//						p.getY());
//				Graph<V, E> graph = vv.getGraphLayout().getGraph();
//				// here the original author put functionality to try and form an
//				// edge between two vertexes
//				if (vertex != null && startVertex != null) {
//					
//					graph.addEdge(edgeFactory.create(), startVertex, vertex,
//							edgeIsDirected);
//					vv.repaint();
//					
//					//if the mouse is released on an existing vertex, clear the attribute
//				}else if(vertex!=null){
//					attributeToCreate=null;
//					
//					//otherwise if the attribute is not null, create a new Vertex here containing the attribute
//				}else if(attributeToCreate!=null){
//					;
////                  V newVertex = vertexFactory.create();
////                  layout = vv.getModel().getGraphLayout();
////                  graph.addVertex(newVertex);
////                  layout.setLocation(newVertex, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint()));
//				}
//			}
//			startVertex = null;
//			down = null;
//			edgeIsDirected = EdgeType.UNDIRECTED;
//			vv.removePostRenderPaintable(edgePaintable);
//			vv.removePostRenderPaintable(arrowPaintable);
//		}
//	}

	/**
	 * If startVertex is non-null, stretch an edge shape between startVertex and
	 * the mouse pointer to simulate edge creation
	 */
//	@SuppressWarnings("unchecked")
//	public void mouseDragged(MouseEvent e) {
//		if (checkModifiers(e)) {
//			if (startVertex != null) {
//				transformEdgeShape(down, e.getPoint());
//				if (edgeIsDirected == EdgeType.DIRECTED) {
//					transformArrowShape(down, e.getPoint());
//				}
//			}
//			VisualizationViewer<V, E> vv = (VisualizationViewer<V, E>) e
//					.getSource();
//			vv.repaint();
//		}
//	}

	/**
	 * If the mouse is pressed in an empty area, create a new vertex there. If
	 * the mouse is pressed on an existing vertex, prepare to create an edge
	 * from that vertex to another
	 */
//	@SuppressWarnings("unchecked")
//	public void mousePressed(MouseEvent e) {
//		if (checkModifiers(e)) {
//			final VisualizationViewer<V, E> vv = (VisualizationViewer<V, E>) e
//					.getSource();
//			final Point2D p = e.getPoint();
//			GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
//			if (pickSupport != null) {
//				Graph<V, E> graph = vv.getModel().getGraphLayout().getGraph();
//				// set default edge type
//				if (graph instanceof DirectedGraph) {
//					edgeIsDirected = EdgeType.DIRECTED;
//				} else {
//					edgeIsDirected = EdgeType.UNDIRECTED;
//				}
//
//				final V vertex = pickSupport.getVertex(vv.getModel()
//						.getGraphLayout(), p.getX(), p.getY());
//				System.out.println("pX: " + p.getX() + " pY: " + p.getY());
//				if (vertex != null) { // get ready to make an edge
//					startVertex = vertex;
//					down = e.getPoint();
//					transformEdgeShape(down, down);
//					vv.addPostRenderPaintable(edgePaintable);
//					if ((e.getModifiers() & MouseEvent.SHIFT_MASK) != 0
//							&& vv.getModel().getGraphLayout().getGraph() instanceof UndirectedGraph == false) {
//						edgeIsDirected = EdgeType.DIRECTED;
//					}
//					if (edgeIsDirected == EdgeType.DIRECTED) {
//						transformArrowShape(down, e.getPoint());
//						vv.addPostRenderPaintable(arrowPaintable);
//					}
//				} // here I commented out code that would cause a vertex to be
//					// created
//					// on mouse clicks - they should be created by dragging an
//					// available attribute into the screen
//
//				// else { // make a new vertex
//				//
//				// V newVertex = vertexFactory.create();
//				// Layout<V,E> layout = vv.getModel().getGraphLayout();
//				// graph.addVertex(newVertex);
//				// layout.setLocation(newVertex,
//				// vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint()));
//				// }
//			}
//			vv.repaint();
//		}
//	}

	/**
	 * code lifted from PluggableRenderer to move an edge shape into an
	 * arbitrary position
	 */
//	private void transformEdgeShape(Point2D down, Point2D out) {
//		float x1 = (float) down.getX();
//		float y1 = (float) down.getY();
//		float x2 = (float) out.getX();
//		float y2 = (float) out.getY();
//
//		AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
//
//		float dx = x2 - x1;
//		float dy = y2 - y1;
//		float thetaRadians = (float) Math.atan2(dy, dx);
//		xform.rotate(thetaRadians);
//		float dist = (float) Math.sqrt(dx * dx + dy * dy);
//		xform.scale(dist / rawEdge.getBounds().getWidth(), 1.0);
//		edgeShape = xform.createTransformedShape(rawEdge);
//	}

//	private void transformArrowShape(Point2D down, Point2D out) {
//		float x1 = (float) down.getX();
//		float y1 = (float) down.getY();
//		float x2 = (float) out.getX();
//		float y2 = (float) out.getY();
//
//		AffineTransform xform = AffineTransform.getTranslateInstance(x2, y2);
//
//		float dx = x2 - x1;
//		float dy = y2 - y1;
//		float thetaRadians = (float) Math.atan2(dy, dx);
//		xform.rotate(thetaRadians);
//		arrowShape = xform.createTransformedShape(rawArrowShape);
//	}
}
