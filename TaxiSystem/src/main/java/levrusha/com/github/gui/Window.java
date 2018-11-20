package levrusha.com.github.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import levrusha.com.github.thread.CarCreationThread;
import levrusha.com.github.thread.RequestCreationThread;
import levrusha.com.github.utils.RequestTrackingTimerTask;

import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private int width = 800;
	private int height = 500;

	private JPanel contentPanel;
	private static JTextArea logArea;
	private static JTextArea crashArea;
	private JLabel requestLabel;
	private JLabel crashlabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Thread carCreation = new Thread(new CarCreationThread());
					carCreation.start();
					
					Window frame = new Window();
					frame.setVisible(true);
					
					Thread requestCreation = new Thread(new RequestCreationThread(logArea));
					requestCreation.start();
					
					Timer timer = new Timer();
					timer.schedule(new RequestTrackingTimerTask(crashArea, logArea), 10000);
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setWindowToCenter();
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		logArea = new JTextArea();
		logArea.setLineWrap(true);
		logArea.setCaretPosition(logArea.getText().length());
		logArea.setEditable(false);
		
		JScrollPane scrollLogArea = new JScrollPane(logArea);
		scrollLogArea.setBounds(10, 41, 765, 164);
		contentPanel.add(scrollLogArea);
		
		requestLabel = new JLabel("Заказы");
		requestLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		requestLabel.setBounds(10, 11, 148, 19);
		contentPanel.add(requestLabel);
		
		crashlabel = new JLabel("Аварийные ситуации");
		crashlabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		crashlabel.setBounds(10, 216, 200, 19);
		contentPanel.add(crashlabel);
		
		crashArea = new JTextArea();
		crashArea.setBounds(10, 246, 382, 204);
		crashArea.setEditable(false);
		contentPanel.add(crashArea);
	

	}
	
	private void setWindowToCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.width) / 2;
		int y = (screenSize.height - this.height) / 2;
		setBounds(x, y, this.width, this.height);
		
	}
}
