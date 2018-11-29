package levrusha.com.github.thread;

import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.storage.RequestJournal;

import static java.lang.Thread.sleep;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class TrackingPendingRequestsThread implements Runnable{

	private Request request;
	private Car car;
	
	private JTextArea crashInfo;
	private JTextArea logInfo;
	
	private ArrayList<JLabel> condition;
	
	public TrackingPendingRequestsThread(JTextArea crash, JTextArea log, ArrayList<JLabel> condition) {
		this.crashInfo = crash;
		this.logInfo = log;
		this.condition = condition;
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			setRequest();
			setFreeCar();

			new Thread(new RunCarThread(request, car, crashInfo, logInfo, condition)).start();
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setFreeCar() {
		if (CarPark.FREECARS.isEmpty()) {
			try {
				sleep(5000);
				setFreeCar();
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
		else {
			this.car = CarPark.FREECARS.first();
		}
	}
	
	private void setRequest() {
		if (RequestJournal.REQUESTS.isEmpty()) {
			try {
				sleep(5000);
				setRequest();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			this.request = RequestJournal.REQUESTS.first();
		}
	}
}
