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
				
		car.setStatus(CarStatus.BUSY);
		
		CarPark.BUSYCARS.add(car);
		CarPark.FREECARS.remove(car);
		
		RequestJournal.ARCHIVE.add(request);
		RequestJournal.REQUESTS.remove(request);
		
		System.out.println("Машина " + car.getId() + " приняла заявку №" + request.getId());
		
		try {
			sleep(50000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		car.setStatus(CarStatus.FREE);
		
		CarPark.FREECARS.add(car);
		CarPark.BUSYCARS.remove(car);		
		
		System.out.println("Машина " + car.getId() + " выполнила заявку №" + request.getId());
		
	}
}
