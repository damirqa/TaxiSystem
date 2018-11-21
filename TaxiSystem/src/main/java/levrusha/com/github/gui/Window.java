package levrusha.com.github.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.storage.CarPark;
import levrusha.com.github.thread.CarCreationThread;
import levrusha.com.github.thread.RequestCreationThread;
import levrusha.com.github.utils.RequestTrackingTimerTask;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private int width = 800;
	private int height = 560;

	private JPanel contentPanel;
	private static JTextArea logArea;
	private static JTextArea crashArea;
	private JLabel requestLabel;
	private JLabel crashlabel;
	private JLabel idCrashCarLabel;
	private JTextField idCrashCarTextField;
	private JButton fixCarButton;

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
		crashArea.setLineWrap(true);
		crashArea.setEditable(false);
		
		JScrollPane scrollCrashArea = new JScrollPane(crashArea);
		scrollCrashArea.setBounds(10, 246, 382, 204);
		contentPanel.add(scrollCrashArea);
		
		idCrashCarLabel = new JLabel("ID сломанной машины:");
		idCrashCarLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		idCrashCarLabel.setBounds(10, 461, 200, 19);
		contentPanel.add(idCrashCarLabel);
		
		idCrashCarTextField = new JTextField();
		idCrashCarTextField.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		idCrashCarTextField.setBounds(214, 460, 25, 23);
		contentPanel.add(idCrashCarTextField);
		idCrashCarTextField.setColumns(10);
		
		fixCarButton = new JButton("Починить машину");
		fixCarButton.setBounds(10, 487, 229, 23);
		contentPanel.add(fixCarButton);
		
		fixCarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idcar = Integer.parseInt(idCrashCarTextField.getText());
				CarPark.BUSYCARS.get(idcar).setStatus(CarStatus.BUSY);
				idCrashCarTextField.setText("");
			}
		});
	}
	
	private void setWindowToCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.width) / 2;
		int y = (screenSize.height - this.height) / 2;
		setBounds(x, y, width, height);
		
	}
}
