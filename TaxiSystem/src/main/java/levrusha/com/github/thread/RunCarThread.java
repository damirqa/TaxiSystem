package levrusha.com.github.thread;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.storage.RequestJournal;

import javax.swing.JTextArea;

public class RunCarThread implements Runnable{
	
	private Request request;
	private Car car;
	private boolean carCrashed;
	private JTextArea crashInfo;
	private JTextArea logInfo;
	
	public RunCarThread(Request request, Car car, JTextArea crash, JTextArea log) {
		this.request = request;
		this.car = car;
		this.crashInfo = crash;
		this.logInfo = log;
	}

	@Override
	public void run() {
				
		car.setStatus(CarStatus.BUSY);
		
		CarPark.BUSYCARS.add(car);
		CarPark.FREECARS.remove(car);
		
		RequestJournal.ARCHIVE.add(request);
		RequestJournal.REQUESTS.remove(request);
		
		this.logInfo.append(" Машина " + car.getId() + " приняла заявку №" + request.getId() + "\n");
		
		System.out.println("Машина " + car.getId() + " приняла заявку №" + request.getId());
		
		int condition = (int)(Math.random() * 10);
		
		if (condition % 10 == 0) {
			carCrashed = true;
		} else {
			carCrashed = false;
		}
		
		Thread carTracker = new Thread(new CarConditionTrackerThread(request, car, carCrashed, crashInfo));
		carTracker.start();
	}
}
