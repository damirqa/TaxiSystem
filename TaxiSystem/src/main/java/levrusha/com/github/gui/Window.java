package levrusha.com.github.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import levrusha.com.github.thread.CarCreationThread;
import levrusha.com.github.thread.RequestCreationThread;



@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private int width = 800;
	private int height = 500;

	private JPanel contentPanel;
	private static JTextArea logArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Window frame = new Window();
					frame.setVisible(true);
					
					Thread carCreation = new Thread(new CarCreationThread());
					carCreation.start();
					
					Thread requestCreation = new Thread(new RequestCreationThread(logArea));
					requestCreation.start();
					
				
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
		
		JScrollPane scrollLogArea = new JScrollPane(logArea);
		scrollLogArea.setBounds(10, 11, 765, 164);
		contentPanel.add(scrollLogArea);

	}
	
	private void setWindowToCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.width) / 2;
		int y = (screenSize.height - this.height) / 2;
		setBounds(x, y, this.width, this.height);
		
	}
}
