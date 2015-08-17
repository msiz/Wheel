package com.sms.wheel.model;

import java.awt.Image;

public class Option {

	private final Image image;
	private final String title;
	
	public Option(Image image, String title) {
		this.image = image;
		this.title = title;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return title;
	}
}
