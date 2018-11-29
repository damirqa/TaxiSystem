package levrusha.com.github.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
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
	
	private int width = 960;
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
	private JLabel gasVolumeLabel;
	private JLabel taxiLabel;
	private JButton fixCarButton;
	private JButton fixGasVolume;
	private JTextField idCrashCarTextField;
	private JTextField gasVolumeField;
		
	private static Date date;
	private static JTextArea logArea;
	private static JTextArea crashArea;
	private static JTextField timeClockTextField;
	private static JTextField repairsTextField;
	private static JTextField incomeTextField;
	private static JTextField profitTextField;
	
	private JLabel car1;
	private JLabel car2;
	private JLabel car3;
	private JLabel car4;
	private JLabel car5;
	private JLabel car6;
	private JLabel car7;
	private JLabel car8;
	private JLabel car9;
	private JLabel car10;
	
	private JLabel conditionCar1;
	private JLabel conditionCar2;
	private JLabel conditionCar3;
	private JLabel conditionCar4;
	private JLabel conditionCar5;
	private JLabel conditionCar6;
	private JLabel conditionCar7;
	private JLabel conditionCar8;
	private JLabel conditionCar9;
	private JLabel conditionCar10;
	
	private static ArrayList<JLabel> condition = new ArrayList<>();
	
	
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
					timer.schedule(new RequestTrackingTimerTask(crashArea, logArea, condition), 10000);
					
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
		
		gasVolumeLabel = new JLabel("Количество бензина:");
		gasVolumeLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		gasVolumeLabel.setBounds(245, 460, 180, 23);
		contentPanel.add(gasVolumeLabel);
		
		gasVolumeField = new JTextField();
		gasVolumeField.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 18));
		gasVolumeField.setBounds(430, 460, 25, 23);
		contentPanel.add(gasVolumeField);
		
		fixGasVolume = new JButton("Заправить машину");
		fixGasVolume.setBounds(245, 487, 210, 23);
		contentPanel.add(fixGasVolume);
		
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
		
		car1 = new JLabel("Машина 1 -");
		car1.setBounds(790, 40, 65, 25);
		contentPanel.add(car1);
		
		conditionCar1 = new JLabel(" Ожидает");
		conditionCar1.setOpaque(true);
		conditionCar1.setBackground(Color.YELLOW);
		conditionCar1.setBounds(860, 40, 80, 25);
		condition.add(conditionCar1);
		contentPanel.add(conditionCar1);
		
		car2 = new JLabel("Машина 2 -");
		car2.setBounds(790, 70, 65, 25);
		contentPanel.add(car2);
		
		conditionCar2 = new JLabel(" Ожидает");
		conditionCar2.setOpaque(true);
		conditionCar2.setBackground(Color.YELLOW);
		conditionCar2.setBounds(860, 70, 80, 25);
		condition.add(conditionCar2);
		contentPanel.add(conditionCar2);
		
		car3 = new JLabel("Машина 3 -");
		car3.setBounds(790, 100, 65, 25);
		contentPanel.add(car3);
		
		conditionCar3 = new JLabel(" Ожидает");
		conditionCar3.setOpaque(true);
		conditionCar3.setBackground(Color.YELLOW);
		conditionCar3.setBounds(860, 100, 80, 25);
		condition.add(conditionCar3);
		contentPanel.add(conditionCar3);
		
		car4 = new JLabel("Машина 4 -");
		car4.setBounds(790, 130, 65, 25);
		contentPanel.add(car4);
		
		conditionCar4 = new JLabel(" Ожидает");
		conditionCar4.setOpaque(true);
		conditionCar4.setBackground(Color.YELLOW);
		conditionCar4.setBounds(860, 130, 80, 25);
		condition.add(conditionCar4);
		contentPanel.add(conditionCar4);
		
		car5 = new JLabel("Машина 5 -");
		car5.setBounds(790, 160, 65, 25);
		contentPanel.add(car5);
		
		conditionCar5 = new JLabel(" Ожидает");
		conditionCar5.setOpaque(true);
		conditionCar5.setBackground(Color.YELLOW);
		conditionCar5.setBounds(860, 160, 80, 25);
		condition.add(conditionCar5);
		contentPanel.add(conditionCar5);
		
		car6 = new JLabel("Машина 6 -");
		car6.setBounds(790, 190, 65, 25);
		contentPanel.add(car6);
		
		conditionCar6 = new JLabel(" Ожидает");
		conditionCar6.setOpaque(true);
		conditionCar6.setBackground(Color.YELLOW);
		conditionCar6.setBounds(860, 190, 80, 25);
		condition.add(conditionCar6);
		contentPanel.add(conditionCar6);
		
		car7 = new JLabel("Машина 7 -");
		car7.setBounds(790, 220, 65, 25);
		contentPanel.add(car7);
		
		conditionCar7 = new JLabel(" Ожидает");
		conditionCar7.setOpaque(true);
		conditionCar7.setBackground(Color.YELLOW);
		conditionCar7.setBounds(860, 220, 80, 25);
		condition.add(conditionCar7);
		contentPanel.add(conditionCar7);
		
		car8 = new JLabel("Машина 8 -");
		car8.setBounds(790, 250, 65, 25);
		contentPanel.add(car8);
		
		conditionCar8 = new JLabel(" Ожидает");
		conditionCar8.setOpaque(true);
		conditionCar8.setBackground(Color.YELLOW);
		conditionCar8.setBounds(860, 250, 80, 25);
		condition.add(conditionCar8);
		contentPanel.add(conditionCar8);
		
		car9 = new JLabel("Машина 9 -");
		car9.setBounds(790, 280, 65, 25);
		contentPanel.add(car9);
		
		conditionCar9 = new JLabel(" Ожидает");
		conditionCar9.setOpaque(true);
		conditionCar9.setBackground(Color.YELLOW);
		conditionCar9.setBounds(860, 280, 80, 25);
		condition.add(conditionCar9);
		contentPanel.add(conditionCar9);
		
		car10 = new JLabel("Машина 10 -");
		car10.setBounds(790, 310, 72, 25);
		contentPanel.add(car10);
		
		conditionCar10 = new JLabel(" Ожидает");
		conditionCar10.setOpaque(true);
		conditionCar10.setBackground(Color.YELLOW);
		conditionCar10.setBounds(860, 310, 80, 25);
		condition.add(conditionCar10);
		contentPanel.add(conditionCar10);
		
		fixCarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idcar = Integer.parseInt(idCrashCarTextField.getText());
				CarPark.BUSYCARS.get(idcar).setStatus(CarStatus.BUSY);
				idCrashCarTextField.setText("");
				crashArea.setCaretPosition(crashArea.getText().length());
			}
		});
		
		fixGasVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idcar = Integer.parseInt(idCrashCarTextField.getText());
				int volume = Integer.parseInt(gasVolumeField.getText());
				
				Car car = CarPark.BUSYCARS.get(idcar);
				car.setGasolineVolume(volume);
				car.setStatus(CarStatus.BUSY);
				gasVolumeField.setText("");
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
