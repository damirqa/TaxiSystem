package levrusha.com.github.utils;

import java.util.TimerTask;

import javax.swing.JTextArea;

import levrusha.com.github.thread.TrackingPendingRequestsThread;

public class RequestTrackingTimerTask extends TimerTask	{
	
	private JTextArea crashInfo;
	private JTextArea logInfo;
	
	public RequestTrackingTimerTask(JTextArea crash, JTextArea log) {
		this.crashInfo = crash;
		this.logInfo = log;
	}

	@Override
	public void run() {
		Thread trackingPendingRequests = new Thread(new TrackingPendingRequestsThread(this.crashInfo, this.logInfo));
		trackingPendingRequests.start();		
	}
}
