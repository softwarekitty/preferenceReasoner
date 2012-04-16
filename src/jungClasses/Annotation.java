package jungClasses;

/*
 * Copyright (c) 2005, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 *
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 *
 * 
 */

import java.awt.Paint;
import java.awt.geom.Point2D;

/**
 * stores an annotation, either a shape or a string
 * 
 * @author Tom Nelson - tomnelson@dev.java.net
 *
 * @param <T>
 */
public class Annotation<T,V> {
	
	protected T annotation;
	protected Paint paint;
	protected Point2D location;
	protected V vertex;
	protected Layer layer;
	protected boolean fill;
	public static enum Layer { LOWER, UPPER }
	
	
	
	public Annotation(T annotation, Layer layer2, Paint paint, 
			boolean fill, Point2D location, V vertex) {
		this.annotation = annotation;
		this.layer = layer2;
		this.paint = paint;
		this.fill = fill;
		this.location = location;
		this.vertex=vertex;
	}
	/**
	 * @return the annotation
	 */
	public T getAnnotation() {
		return annotation;
	}
	/**
	 * @param annotation the annotation to set
	 */
	public void setAnnotation(T annotation) {
		this.annotation = annotation;
	}
	/**
	 * @return the location
	 */
	public Point2D getLocation() {
		return location;
	}
	/**
	 * @return the layer
	 */
	public Layer getLayer() {
		return layer;
	}
	/**
	 * @param layer the layer to set
	 */
	public void setLayer(Layer layer) {
		this.layer = layer;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Point2D location) {
		this.location = location;
	}
	public V getVertex() {
		return vertex;
	}
	public void setVertex(V vertex) {
		this.vertex = vertex;
	}
	/**
	 * @return the paint
	 */
	public Paint getPaint() {
		return paint;
	}
	/**
	 * @param paint the paint to set
	 */
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	/**
	 * @return the fill
	 */
	public boolean isFill() {
		return fill;
	}
	/**
	 * @param fill the fill to set
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	@Override
	public String toString(){
		return annotation.toString();
	}
	

}

