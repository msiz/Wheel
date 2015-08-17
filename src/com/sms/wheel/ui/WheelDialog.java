package com.sms.wheel.ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WheelDialog extends JDialog {

	private WheelComponent wheel;
	
	public WheelDialog() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setTitle("Suits Wheel");
		setAlwaysOnTop(false);
		createContent();
		pack();
	}
	
	private void createContent() {
		wheel = new WheelComponent();
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(wheel, BorderLayout.CENTER);
		setContentPane(panel);
	}
	
	public void setSelection(int index) {
		wheel.setSelection(index);
	}
	
	public void clearSelection() {
		wheel.setSelection(-1);
	}
	
	public int getSelection() {
		return wheel.getSelection();
	}
	
	public int getOptionsCount() {
		return wheel.getOptionsCount();
	}
}
