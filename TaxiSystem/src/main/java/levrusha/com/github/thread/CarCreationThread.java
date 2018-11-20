package levrusha.com.github.thread;

import levrusha.com.github.model.Car;
import levrusha.com.github.storage.CarPark;

public class CarCreationThread implements Runnable {
	
	@Override
	public synchronized void run() {
		String models[] = {"Ford Focus", "Skoda Octavia", "Hyundai Solaris", "Kia Rio", "Renault Logan", "Lada Largus", "Lada Priora", "Lada Granta", "Kia ceed", "Nissan Almera"};
		String plates[] = {"E102CH102", "B170MM02", "K002CT02", "E321EO102", "H095XA102", "A232AB02", "B721YE102", "M798MP02", "C086CC102", "K100AE02"};
		
		for(int i = 0; i < 10; i++) {
			Car car = new Car();
			car.setModel(models[i]);
			car.setplateLicense(plates[i]);
			CarPark.FREECARS.add(car);
			CarPark.CARS.add(car);
			System.out.println(car);
		}
	}
}
