package com.sms.wheel.model;

import com.sms.wheel.resources.ResourceLoader;

public class Suits {

	public static final Option[] OPTIONS = new Option[] {
		new Option(ResourceLoader.load(ResourceLoader.BLACK), 	"Black"),
		new Option(ResourceLoader.load(ResourceLoader.SPADE), 	"Spades"),
		new Option(ResourceLoader.load(ResourceLoader.CLUB), 	"Clubs"),
		new Option(ResourceLoader.load(ResourceLoader.DIAMOND), "Diamonds"),
		new Option(ResourceLoader.load(ResourceLoader.HEART), 	"Hearts"),
		new Option(ResourceLoader.load(ResourceLoader.RED), 	"Red")
	};
}
