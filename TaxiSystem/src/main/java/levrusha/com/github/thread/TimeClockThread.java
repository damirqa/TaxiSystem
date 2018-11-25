package levrusha.com.github.thread;

import java.util.Date;

import javax.swing.JTextField;

import static java.lang.Thread.sleep;

import java.text.SimpleDateFormat;

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
			SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM HH:mm:ss");
			this.timeClockField.setText(dt1.format(date));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
