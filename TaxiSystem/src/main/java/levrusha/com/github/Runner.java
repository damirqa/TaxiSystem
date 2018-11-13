package levrusha.com.github;

import levrusha.com.github.model.Car;
import levrusha.com.github.storage.CarPark;

//import java.util.Timer;

//import levrusha.com.github.thread.CarCreationThread;
//import levrusha.com.github.thread.RequestCreationThread;
//import levrusha.com.github.utils.RequestTrackingTimerTask;

public class Runner {

	public static void main(String[] args) {

//		Thread carCreation = new Thread(new CarCreationThread());
//		carCreation.start();
//		
//		Thread requestCreation = new Thread(new RequestCreationThread());
//		requestCreation.start();
//		
//		Timer timer = new Timer();
//		timer.schedule(new RequestTrackingTimerTask(), 10000);
		
		String models[] = {"Ford Focus", "Skoda Octavia", "Hyundai Solaris", "Kia Rio", "Renault Logan", "Lada Largus", "Lada Priora", "Lada Granta", "Kia ceed", "Nissan Almera"};
		String plates[] = {"E102CH102", "B170MM02", "K002CT02", "E321EO102", "H095XA102", "A232AB02", "B721YE102", "M798MP02", "C086CC102", "K100AE02"};
		
		for(int i = 0; i < 10; i++) {
			Car car = new Car();
			car.setModel(models[i]);
			car.setplateLicense(plates[i]);
			CarPark.cars.add(car);
			System.out.println(car);
		}
		
	}
}