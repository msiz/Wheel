package com.sms.wheel.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.sms.wheel.model.Suits;
import com.sms.wheel.model.Wheel;
import com.sms.wheel.resources.ResourceLoader;

@SuppressWarnings("serial")
public class WheelComponent extends Component {

	private static final Dimension DEFAULT_SIZE = new Dimension(500, 500);
	private Wheel wheel;
	private int index = -1;
	
	public WheelComponent() {
		setPreferredSize(DEFAULT_SIZE);
		wheel = new Wheel(DEFAULT_SIZE.width, DEFAULT_SIZE.height, Suits.OPTIONS);
		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				wheel = new Wheel(getWidth(), getHeight(), Suits.OPTIONS);
				wheel.setSelection(index);
			}
		});
	}
	
	public void paint(Graphics g) {
		g.drawImage(getImage(), 0, 0, this);
	}
	
	private Image getImage() {
		return WheelPainter.paintWheel(wheel);
	}
	
	public void setSelection(int index) {
		this.index = index;
		wheel.setSelection(index);
		playSound();
		repaint();
	}
	
	public int getSelection() {
		return wheel.getSelection();
	}
	
	public int getOptionsCount() {
		return Suits.OPTIONS.length;
	}
	
	private void playSound() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(ResourceLoader.loadSound(ResourceLoader.AUDIO));
			clip.start();
		} catch (Exception e) {
			throw new RuntimeException("Cannot play sound", e);
		}
	}
}
