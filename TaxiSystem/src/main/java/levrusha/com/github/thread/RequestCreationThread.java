package levrusha.com.github.thread;

import java.util.Random;

import javax.swing.JTextArea;

import static java.lang.Thread.sleep;

import levrusha.com.github.model.Request;
import levrusha.com.github.storage.RequestJournal;

public class RequestCreationThread implements Runnable {
	
	private JTextArea log;
	
	public RequestCreationThread(JTextArea log) {
		this.log = log;
	}

	@Override
	public void run() {
		
		while(true) {
			
			Request request = new Request();
			
			RequestJournal.REQUESTS.add(request);
			
			log.append(" " + request.toString() + "\n");
			
			int timeBetweenRequests = new Random().nextInt(10000 - 5000) + 5000;
			
			try {
				sleep(timeBetweenRequests);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

}
