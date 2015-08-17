package com.sms.wheel.model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Wheel {
	
	private static final int PADDING = 10;
	private static final int HALF_PADDING = PADDING / 2;
	
	private final int width;
	private final int height;
	private final Option[] options;
	
	private DrawInfo[] info;
	private int selection = -1;

	public Wheel(int width, int height, Option...options) {
		this.width = width;
		this.height = height;
		this.options = options;
		
		buildPolygons();
	}
	
	private void buildPolygons() {
		
		List<DrawInfo> list = new ArrayList<DrawInfo>();
		int count = options.length;
		
		int lx = (width - PADDING) / 2;
		int ly = (height - PADDING) / 2;
		
		Point center = new Point(lx + HALF_PADDING, ly + HALF_PADDING);
		
		double angle = 2 * Math.PI / count;
		
		int x = lx + HALF_PADDING;
		int y = HALF_PADDING;
		
		for (int i = 1; i <= count; i++) {
			
			Option option = options[i-1];
			double alpha = angle * i;
			double halfAlpha = angle * (i - 0.5);
			int newX = center.x + (int)(Math.sin(alpha) * lx);
			int newY = center.y - (int)(Math.cos(alpha) * ly);
			int halfX = center.x + (int)(Math.sin(halfAlpha) * lx);
			int halfY = center.y - (int)(Math.cos(halfAlpha) * ly);
			
			Polygon polygon = new Polygon(
					new int[] {center.x, x, halfX, newX}, 
					new int[] {center.y, y, halfY, newY}, 
					4);
			
			Image image = option.getImage();
			String title = option.getTitle();
			Point imagePosition = new Point();
			Point titlePosition = new Point();
			
			if (image != null) {
				imagePosition.x = center.x + (int)(Math.sin(angle * (i - 0.5)) * lx * 0.65) - image.getWidth(null) / 2;
				imagePosition.y = center.y - (int)(Math.cos(angle * (i - 0.5)) * ly * 0.65) - image.getHeight(null) / 2;
			}
			
			if (title != null) {
				titlePosition.x = center.x + (int)(Math.sin(angle * (i - 0.5)) * lx * 0.9);
				titlePosition.y = center.y - (int)(Math.cos(angle * (i - 0.5)) * ly * 0.9);
			}
			
			
			DrawInfo info = new DrawInfo(polygon, imagePosition, image, titlePosition, title, halfAlpha);
			list.add(info);
			
			x = newX;
			y = newY;
		}
		
		this.info = list.toArray(new DrawInfo[count - 1]);
	}
	
	public DrawInfo[] getDrawingInformation() {
		return this.info;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public int getSelection() {
		return selection;
	}

	public void setSelection(int selection) {
		this.selection = selection;
	}
}
