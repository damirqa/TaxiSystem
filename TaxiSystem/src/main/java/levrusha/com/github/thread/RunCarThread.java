package levrusha.com.github.thread;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.storage.RequestJournal;

import static java.lang.Thread.sleep;

public class RunCarThread implements Runnable{
	
	private Request request;
	private Car car;
	
	public RunCarThread(Request request, Car car) {
		this.request = request;
		this.car = car;
	}

	@Override
	public void run() {
		
		RequestJournal.expectingRequest.remove(request);
		RequestJournal.archiveRequest.add(request);
		
		car.setStatus(CarStatus.BUSY);
		CarPark.freeCars.remove(car);
		CarPark.busyCars.add(car);
		
		System.out.println("Машина " + car.getId() + " приняла заявку №" + request.getId());
		
		try {
			sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		car.setStatus(CarStatus.FREE);
		CarPark.busyCars.remove(car);
		CarPark.freeCars.add(car);
		
		System.out.println("Машина " + car.getId() + " выполнила заявку №" + request.getId());
		
	}
}
