package com.sms.wheel.resources;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class ResourceLoader {

	public static final String SPADE = "spade.png";
	public static final String CLUB = "club.png";
	public static final String DIAMOND = "diamond.png";
	public static final String HEART = "heart.png";
	public static final String BLACK = "black.png";
	public static final String RED = "red.png";
	public static final String AUDIO = "sms.wav";
	
	public static Image load(String resource) {
		try (InputStream stream = ResourceLoader.class.getResourceAsStream(resource)) {
			return ImageIO.read(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cannot load image resource: " + resource, e);
		}
	}
	
	public static AudioInputStream loadSound(String resource) {
		
		try {
			return AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resource));
		} catch (Exception e) {
			throw new RuntimeException("Cannot load sound resource: " + resource, e);
		}
	}
	
}
