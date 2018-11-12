package levrusha.com.github.thread;

import levrusha.com.github.exception.NothingException;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.storage.RequestJournal;

import static java.lang.Thread.sleep;

public class TrackingPendingRequestsThread implements Runnable{
	
	private Request request;
	private Car car;

	@Override
	public void run() {
		
		while(true) {
			
			setRequest();
			setFreeCar();
			
			new Thread(new RunCarThread(request, car)).start();
			
			//this.поток должен заснуть, чтобы поток runcarthread мог выполнить удаление из списков машину и заявку
			//иначе будет это
			//Машина 1 приняла заявку №1
			//Машина 1 приняла заявку №1
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setFreeCar() {
		if (CarPark.freeCars.isEmpty()) {
			try {
				sleep(3000);
				setFreeCar();
			} catch (InterruptedException e) {
			}
		}
		else {
			this.car = CarPark.freeCars.first();
		}
	}
	
	private void setRequest() {
		if (RequestJournal.expectingRequest.isEmpty()) {
			try {
				sleep(3000);
				setRequest();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			this.request = RequestJournal.expectingRequest.first();
		}
	}
}
