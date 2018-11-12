package levrusha.com.github.thread;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.storage.CarPark;

public class CarCreationThread implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			Car car = new Car();
			
			car.setCounter();
			car.setId();
			car.setStatus(CarStatus.FREE);
			
			CarPark.freeCars.add(car);
		}
	}
}
