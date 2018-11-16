package levrusha.com.github.thread;

import java.util.Random;
import static java.lang.Thread.sleep;

import levrusha.com.github.model.Request;
import levrusha.com.github.storage.RequestJournal;

public class RequestCreationThread implements Runnable{

	@Override
	public void run() {
		
		while(true) {
			
			Request request = new Request();
			
			RequestJournal.REQUESTS.add(request);
			
			System.out.println(request);
			
			int timeBetweenRequests = new Random().nextInt(10000 - 5000) + 5000;
			
			try {
				sleep(timeBetweenRequests);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

}
