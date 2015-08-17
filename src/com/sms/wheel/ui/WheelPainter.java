package com.sms.wheel.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;

import com.sms.wheel.model.DrawInfo;
import com.sms.wheel.model.Wheel;

public class WheelPainter {
	
	private static final boolean DRAW_TITLES = false;
	
	private static final Color BACKGROUND = new Color(25, 143, 191);
	private static final Color WHEEL = new Color(4, 82, 114);
	private static final Color PERIMETER = Color.BLACK;
	private static final Color SELECTION = Color.YELLOW;
	private static final Color COVER = new Color(80, 80, 80, 200);
	private static final Stroke BASIC_STROKE = new BasicStroke(3);
	private static final Stroke SELECTION_STROKE = new BasicStroke(5);
	
	private static final TransparentImageFilter FILTER = new TransparentImageFilter(Color.WHITE);

	public static void paint(Graphics2D g, Wheel wheel) {
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(BASIC_STROKE);
		g.setColor(PERIMETER);
		
		int selection = wheel.getSelection();
		DrawInfo[] information = wheel.getDrawingInformation();
		for (DrawInfo info: information) {
			
			Polygon polygon = info.getPolygon();
			
			g.setColor(WHEEL);
			g.fillPolygon(polygon);
			
			g.setColor(PERIMETER);
			g.drawPolygon(polygon);
			
			Image image = info.getImage();
			if (image != null) {
				Point position = info.getImagePosition();
				g.drawImage(image, position.x, position.y, null);
			}
			
			if (DRAW_TITLES) {
				String title = info.getTitle();
				if (title != null) {
					double angle = info.getTitleAngle();
					Point position = info.getTitlePosition();
					g.rotate(angle, position.x, position.y);
					g.drawString(title, position.x, position.y);
					g.rotate(-angle, position.x, position.y);
				}
			}
		}
		
		if (selection > -1) {
			
			g.setColor(COVER);
			
			for (int i = 0; i < information.length; i++) {
				DrawInfo info = information[i];
				if (selection != i) {
					g.fillPolygon(info.getPolygon());
				}
			}
			
			DrawInfo info = information[selection];
			g.setColor(SELECTION);
			g.setStroke(SELECTION_STROKE);
			g.drawPolygon(info.getPolygon());
		}
	}
	
	public static Image paintWheel(Wheel wheel) {
		
		BufferedImage image = new BufferedImage(wheel.getWidth(), wheel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D imageGraphics = image.createGraphics();
		imageGraphics.setColor(BACKGROUND);
		imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		paint(imageGraphics, wheel);
		
		ImageProducer ip = new FilteredImageSource(image.getSource(), FILTER);
		Image transparent = Toolkit.getDefaultToolkit().createImage(ip);
		
		return transparent;
	}
	
}
