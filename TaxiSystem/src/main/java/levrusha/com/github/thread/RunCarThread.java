package levrusha.com.github.thread;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.storage.RequestJournal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RunCarThread implements Runnable{
	
	private Request request;
	private Car car;
	private boolean carCrashed;
	private JTextArea crashInfo;
	private JTextArea logInfo;
	private ArrayList<JLabel> condition;
	
	public RunCarThread(Request request, Car car, JTextArea crash, JTextArea log, ArrayList<JLabel> condition) {
		this.request = request;
		this.car = car;
		this.crashInfo = crash;
		this.logInfo = log;
		this.condition = condition;
	}

	@Override
	public void run() {
				
		car.setStatus(CarStatus.BUSY);
		
		CarPark.BUSYCARS.put(car.getId(), car);
		CarPark.FREECARS.remove(car);
		
		RequestJournal.ARCHIVE.add(request);
		RequestJournal.REQUESTS.remove(request);

		this.logInfo.append(" Машина " + car.getId() + " приняла заявку №" + request.getId() + "\n");
		this.logInfo.setCaretPosition(this.logInfo.getText().length());
		
		condition.get(car.getId() - 1).setText(" Заявка" + request.getId());
		condition.get(car.getId() - 1).setBackground(Color.GREEN);
		
		//System.out.println("Машина " + car.getId() + " приняла заявку №" + request.getId());
		
		int condition = (int)(Math.random() * 10);
		
		if (condition % 10 == 0) {
			carCrashed = true;
		} else {
			carCrashed = false;
		}
		
		Thread carTracker = new Thread(new CarConditionTrackerThread(request, car, carCrashed, crashInfo, logInfo, this.condition));
		carTracker.start();
	}
}
