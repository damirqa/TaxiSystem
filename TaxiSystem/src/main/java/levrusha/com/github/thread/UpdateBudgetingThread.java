package levrusha.com.github.thread;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import static java.lang.Thread.sleep;

import levrusha.com.github.storage.Budgeting;

public class UpdateBudgetingThread implements Runnable{
	
	private JTextField incomeField;
	private JTextField repairsField;
	private JTextField profitField;
	
	public UpdateBudgetingThread(JTextField income, JTextField repairs, JTextField profit) {
		this.incomeField = income;
		this.repairsField = repairs;
		this.profitField = profit;
	}

	@Override
	public void run() {
		while (true) {
			this.incomeField.setText(Budgeting.INCOME + " руб.");
			this.repairsField.setText(Budgeting.REPAIRS + " руб.");
			this.profitField.setText(Budgeting.PROFIT + " руб.");
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}

}
