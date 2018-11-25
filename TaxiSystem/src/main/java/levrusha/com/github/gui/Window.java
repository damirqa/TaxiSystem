package levrusha.com.github.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Date;
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
import levrusha.com.github.thread.TimeClockThread;
import levrusha.com.github.thread.UpdateBudgetingThread;
import levrusha.com.github.utils.RequestTrackingTimerTask;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private int width = 800;
	private int height = 560;
	
	private JPanel contentPanel;
	private JLabel requestLabel;
	private JLabel crashlabel;
	private JLabel idCrashCarLabel;
	private JLabel timeClockLabel;
	private JLabel calculateLabel;
	private JLabel incomeLabel;
	private JLabel reparsLabel;
	private JLabel profitLabel;
	private JLabel taxiLabel;
	private JButton fixCarButton;
	private JTextField idCrashCarTextField;
		
	private static Date date;
	private static JTextArea logArea;
	private static JTextArea crashArea;
	private static JTextField timeClockTextField;
	private static JTextField repairsTextField;
	private static JTextField incomeTextField;
	private static JTextField profitTextField;
	
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
					
					Thread timeClock = new Thread(new TimeClockThread(date, timeClockTextField));
					timeClock.start();
					
					Thread updateBudgeting = new Thread(new UpdateBudgetingThread(incomeTextField, repairsTextField, profitTextField));
					updateBudgeting.start();
					
				
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
		
		timeClockLabel = new JLabel("Текущее время:");
		timeClockLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		timeClockLabel.setBounds(414, 353, 132, 19);
		contentPanel.add(timeClockLabel);
		
		timeClockTextField = new JTextField();
		timeClockTextField.setBackground(Color.WHITE);
		timeClockTextField.setEditable(false);
		timeClockTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		timeClockTextField.setBounds(556, 353, 86, 20);
		contentPanel.add(timeClockTextField);
		timeClockTextField.setColumns(10);
		
		calculateLabel = new JLabel("Расчеты");
		calculateLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		calculateLabel.setBounds(414, 221, 200, 19);
		contentPanel.add(calculateLabel);
		
		incomeLabel = new JLabel("Доходы:");
		incomeLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		incomeLabel.setBounds(413, 261, 84, 19);
		contentPanel.add(incomeLabel);
		
		reparsLabel = new JLabel("Ремонт машин:");
		reparsLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		reparsLabel.setBounds(414, 291, 132, 19);
		contentPanel.add(reparsLabel);
		
		profitLabel = new JLabel("Прибыль:");
		profitLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		profitLabel.setBounds(414, 321, 84, 19);
		contentPanel.add(profitLabel);
		
		repairsTextField = new JTextField();
		repairsTextField.setBackground(Color.WHITE);
		repairsTextField.setEditable(false);
		repairsTextField.setBounds(556, 293, 86, 20);
		contentPanel.add(repairsTextField);
		repairsTextField.setColumns(10);
		
		incomeTextField = new JTextField();
		incomeTextField.setEditable(false);
		incomeTextField.setColumns(10);
		incomeTextField.setBackground(Color.WHITE);
		incomeTextField.setBounds(556, 263, 86, 20);
		contentPanel.add(incomeTextField);
		
		profitTextField = new JTextField();
		profitTextField.setEditable(false);
		profitTextField.setColumns(10);
		profitTextField.setBackground(Color.WHITE);
		profitTextField.setBounds(556, 323, 86, 20);
		contentPanel.add(profitTextField);
		
		taxiLabel = new JLabel(new ImageIcon(getClass().getResource("/img/Taxi.jpg")));
		taxiLabel.setBounds(415, 400, 353, 40);
		contentPanel.add(taxiLabel);
		
		fixCarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idcar = Integer.parseInt(idCrashCarTextField.getText());
				CarPark.BUSYCARS.get(idcar).setStatus(CarStatus.BUSY);
				idCrashCarTextField.setText("");
				crashArea.setCaretPosition(crashArea.getText().length());
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
