package levrusha.com.github.thread;

import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.storage.RequestJournal;

import static java.lang.Thread.sleep;

public class TrackingPendingRequestsThread implements Runnable{

	Request request;
	Car car;
	
	@Override
	public void run() {
		
		while(true) {
			
			setRequest();
			setFreeCar();

			new Thread(new RunCarThread(request, car)).start();
			
			try {
				sleep(5000);
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
