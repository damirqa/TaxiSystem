package levrusha.com.github;

import java.util.Random;

import levrusha.com.github.storage.StreetsList;
import levrusha.com.github.thread.CarCreationThread;
import levrusha.com.github.thread.RequestCreationThread;

public class Runner {

	public static void main(String[] args) {

		Thread carCreation = new Thread(new CarCreationThread());
		carCreation.start();
		
		
		
		
		Thread requestCreation = new Thread(new RequestCreationThread());
		requestCreation.start();
//		
//		Timer timer = new Timer();
//		timer.schedule(new RequestTrackingTimerTask(), 10000);
		
		
		
	}
}