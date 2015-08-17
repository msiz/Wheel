package com.sms.wheel.model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;

public class DrawInfo {

	private final Polygon polygon;
	private final Point imagePosition;
	private final Image image;
	private final Point titlePosition;
	private final String title;
	private final double titleAngle;
	
	protected DrawInfo(Polygon polygon, Point imagePosition, Image image, Point titlePosition, String title, double titleAngle) {
		this.polygon = polygon;
		this.imagePosition = imagePosition;
		this.image = image;
		this.titlePosition = titlePosition;
		this.title = title;
		this.titleAngle = titleAngle;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public Point getImagePosition() {
		return imagePosition;
	}

	public Image getImage() {
		return image;
	}

	public Point getTitlePosition() {
		return titlePosition;
	}

	public String getTitle() {
		return title;
	}

	public double getTitleAngle() {
		return titleAngle;
	}
}
