package com.zj.study.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Arrays;
import java.util.List;
//from   ww w  .  ja  v  a2s  .co m
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

class SwingWorkerProcessor extends SwingWorker<Integer, Integer> {
	private final SwingWorkerFrame frame;
	private int iteration;
	private int intervalInMillis;

	public SwingWorkerProcessor(SwingWorkerFrame frame, int iteration, int intervalInMillis) {
		this.frame = frame;
		this.iteration = iteration;

		if (this.iteration <= 0) {
			this.iteration = 10;
		}
		this.intervalInMillis = intervalInMillis;
		if (this.intervalInMillis <= 0) {
			this.intervalInMillis = 1000;
		}
	}

	@Override
	protected Integer doInBackground() throws Exception {
		int sum = 0;
		for (int counter = 1; counter <= iteration; counter++) {
			sum = sum + counter;
			this.publish(counter);
			if (Thread.interrupted()) {
				throw new InterruptedException();
			}
			if (this.isCancelled()) {
				break;
			}
			Thread.sleep(intervalInMillis);
		}

		return sum;
	}

	@Override
	protected void process(List<Integer> data) {
		System.out.println(Arrays.toString(data.toArray()));
		for (int counter : data) {
			frame.updateStatus(counter, iteration);
		}
	}

	@Override
	public void done() {
		try {
			frame.doneProcessing();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class SwingWorkerFrame extends JFrame {
	String startMessage = "Please click the   start button...";
	JLabel statusLabel = new JLabel(startMessage);
	JButton startButton = new JButton("Start");
	JButton cancelButton = new JButton("Cancel");
	SwingWorkerProcessor processor;

	public SwingWorkerFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		cancelButton.setEnabled(false);

		contentPane.add(statusLabel, BorderLayout.NORTH);
		contentPane.add(startButton, BorderLayout.WEST);
		contentPane.add(cancelButton, BorderLayout.EAST);

		startButton.addActionListener(e -> startProcessing());
		cancelButton.addActionListener(e -> cancelProcessing());
	}

	public void setButtonStatus(boolean canStart) {
		if (canStart) {
			startButton.setEnabled(true);
			cancelButton.setEnabled(false);
		} else {
			startButton.setEnabled(false);
			cancelButton.setEnabled(true);
		}
	}

	public void startProcessing() {
		setButtonStatus(false);
		processor = new SwingWorkerProcessor(this, 10, 1000);
		processor.execute();
	}

	public void cancelProcessing() {
		processor.cancel(true);
		setButtonStatus(true);
	}

	public void updateStatus(int counter, int total) {
		String msg = "Processing " + counter + "  of  " + total;
		statusLabel.setText(msg);
	}

	public void doneProcessing() throws Exception {
		if (processor.isCancelled()) {
			statusLabel.setText("Process  cancelled ...");
		} else {
			int sum = processor.get();
			statusLabel.setText("Sum  is " + sum);
			setButtonStatus(true);
		}
	}
}

public class SwingWorkerMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SwingWorkerFrame frame = new SwingWorkerFrame();
			frame.pack();
			frame.setVisible(true);
		});
	}
}