package levrusha.com.github.utils;

import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import levrusha.com.github.thread.TrackingPendingRequestsThread;

public class RequestTrackingTimerTask extends TimerTask	{
	
	private JTextArea crashInfo;
	private JTextArea logInfo;
	
	private ArrayList<JLabel> condition;
	
	public RequestTrackingTimerTask(JTextArea crash, JTextArea log, ArrayList<JLabel> condition) {
		this.crashInfo = crash;
		this.logInfo = log;
		this.condition = condition;
	}

	@Override
	public void run() {
		Thread trackingPendingRequests = new Thread(new TrackingPendingRequestsThread(crashInfo, logInfo, condition));
		trackingPendingRequests.start();		
	}
}
