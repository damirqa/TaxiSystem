package levrusha.com.github.thread;

import java.util.Date;

import javax.swing.JTextField;

import static java.lang.Thread.sleep;

public class TimeClockThread implements Runnable {
	
	private Date date;
	private JTextField timeClockField;
	
	public TimeClockThread(Date date, JTextField timeClockField) {
		this.date = date;
		this.timeClockField = timeClockField;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		
		while (true) {
			this.date = new Date();
			this.timeClockField.setText(this.date.toLocaleString());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
