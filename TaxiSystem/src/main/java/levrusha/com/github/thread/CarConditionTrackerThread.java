package levrusha.com.github.thread;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;

public class CarConditionTrackerThread implements Runnable {
	
	private Request request;
	private Car car;
	private boolean carCrashed;
	
	public CarConditionTrackerThread(Request request, Car car, boolean carCrashed) {
		this.request = request;
		this.car = car;
		this.carCrashed = carCrashed;
	}

	@Override
	public void run() {
		if (carCrashed) {
			this.car.setStatus(CarStatus.BROKEN);
			System.out.println("Машина " + this.car.getId() + " сломалась!");
		}
		else {
			car.setStatus(CarStatus.FREE);
			
			CarPark.FREECARS.add(car);
			CarPark.BUSYCARS.remove(car);		
			
			System.out.println("Машина " + car.getId() + " выполнила заявку №" + request.getId());
		}
	}

}
