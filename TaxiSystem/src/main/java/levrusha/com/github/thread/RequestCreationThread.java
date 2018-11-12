package levrusha.com.github.thread;

import java.util.Date;
import java.util.Random;
import static java.lang.Thread.sleep;

import levrusha.com.github.enums.RequestStatus;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.RequestJournal;

public class RequestCreationThread implements Runnable{

	@Override
	public void run() {
		
		while(true) {
			
			Request request = new Request();
			
			request.setCounter();
			request.setId();
			request.setDate(new Date());
			request.setStatus(RequestStatus.PENDING);
			
			RequestJournal.expectingRequest.add(request);
			
			//System.out.println(RequestJournal.expectingRequest.last());
			
			int timeBetweenBids = new Random().nextInt(10000 - 5000) + 5000;
			
			try {
				sleep(timeBetweenBids);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

}
