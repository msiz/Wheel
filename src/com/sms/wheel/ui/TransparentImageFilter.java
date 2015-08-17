package com.sms.wheel.ui;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class TransparentImageFilter extends RGBImageFilter {

	private int rgb;
	
	protected TransparentImageFilter(Color color) {
		this.rgb = color.getRGB() | 0xFFFFFFFF;;
	}
	
    public final int filterRGB(final int x, final int y, final int rgb) {
    	
       return ((rgb | 0xFF000000) == this.rgb) ? 0x00FFFFFF & rgb : rgb;
    }
}
