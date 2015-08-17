package com.sms.wheel;

import javax.swing.UIManager;

import com.sms.wheel.ui.MainFrame;

public class Application {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			throw new RuntimeException("Cannot initialize LookAndFeel", e);
		}
		new MainFrame().setVisible(true);
	}
}
