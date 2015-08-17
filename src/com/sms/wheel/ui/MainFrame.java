package com.sms.wheel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.sms.wheel.model.Option;
import com.sms.wheel.model.Suits;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private WheelDialog dialog;
	
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Suits Wheel");
		createContent();
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		dialog = new WheelDialog();
		dialog.setVisible(true);
	}
	
	private void createContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createButtonsPanel(), BorderLayout.NORTH);
		panel.add(createTimePanel(), BorderLayout.CENTER);
		setContentPane(panel);
	}
	
	private JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
		
		JButton randomSelection = new JButton(new AbstractAction("Random selection") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setSelection(getRandomIndex());
			}
		});
		
		JButton clearSelection = new JButton(new AbstractAction("Clear selection") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setSelection(-1);
			}
		});
		
		final JComboBox<Option> combo = new JComboBox<Option>(Suits.OPTIONS);
		combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setSelection(combo.getSelectedIndex());
			}
		});
		
		panel.add(randomSelection);
		panel.add(clearSelection);
		panel.add(new JLabel("Select:"));
		panel.add(combo);
		
		return panel;
	}
	
	private JPanel createTimePanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
		
		SpinnerModel fromModel = new SpinnerNumberModel(30, 1, 600, 10);
		SpinnerModel toModel = new SpinnerNumberModel(40, 1, 600, 10);
		final JSpinner fromSpinner = new JSpinner(fromModel);
		final JSpinner toSpinner = new JSpinner(toModel);
		
		JButton timerButton = new JButton(new AbstractAction("Start") {
			
			private boolean running = false;
			private ScheduledFuture<?> future;
			private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			private final Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					dialog.setSelection(getRandomIndex());
					scheduleSelection();
				}
			};
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				running = !running;
				putValue(Action.NAME, running ? "Stop" : "Start");
				
				if (running) {
					scheduleSelection();
				} else {
					cancelSelection();
				}
			}
			
			private void scheduleSelection() {
				int from = (int) fromSpinner.getValue();
				int to = (int) toSpinner.getValue();
				
				Random random = new Random();
				long delay = from + random.nextInt(to - from + 1);
				
				future = scheduler.schedule(runnable, delay, TimeUnit.SECONDS);
			}
			
			private void cancelSelection() {
				if (future != null) {
					future.cancel(false);
				}
			}
			
		});
		
		panel.add(new JLabel("Timer:"));
		panel.add(fromSpinner);
		panel.add(new JLabel("-"));
		panel.add(toSpinner);
		panel.add(new JLabel("seconds"));
		panel.add(timerButton);
		
		return panel;
	}
	
	private int getRandomIndex() {
		int max = dialog.getOptionsCount();
		int current = dialog.getSelection();
		Random random = new Random();
		int index = random.nextInt(max);
		while (index == current) {
			index = random.nextInt(max);
		}
		return index;
	}
}
