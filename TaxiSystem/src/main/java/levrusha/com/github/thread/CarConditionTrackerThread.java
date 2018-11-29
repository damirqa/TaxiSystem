package levrusha.com.github.thread;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.Budgeting;
import levrusha.com.github.storage.CarPark;

import static java.lang.Thread.sleep;

import java.awt.Color;
import java.util.ArrayList;

public class CarConditionTrackerThread implements Runnable {
	
	private Request request;
	private Car car;
	private boolean carCrashed;
	private JTextArea crashInfo;
	private JTextArea logInfo;
	private ArrayList<JLabel> condition;
	
	public CarConditionTrackerThread(Request request, Car car, boolean carCrashed, JTextArea crash, JTextArea log, ArrayList<JLabel> condition) {
		this.request = request;
		this.car = car;
		this.carCrashed = carCrashed;
		this.crashInfo = crash;
		this.logInfo = log;
		this.condition = condition;
	}

	@Override
	public void run() {
		
		JLabel conditionCar = this.condition.get(car.getId() - 1);
		
		if (carCrashed) {
			this.car.setStatus(CarStatus.BROKEN);
			
			this.crashInfo.append("Машина №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") сломалась!\n");
			this.crashInfo.setCaretPosition(this.crashInfo.getText().length());
			
			conditionCar.setText(" Сломана");
			conditionCar.setBackground(Color.RED);
			conditionCar.setForeground(Color.WHITE);
			
			//System.out.println("Машина " + this.car.getId() + " сломалась!");
			
			while(this.car.getStatus() == CarStatus.BROKEN) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
			
			Budgeting.REPAIRS += 500;
			
			this.crashInfo.append("Машина №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") почининили!\n");
			conditionCar.setText(" Заявка №" + request.getId());
			conditionCar.setBackground(Color.GREEN);
			conditionCar.setForeground(Color.BLACK);
		}
		
		int gasVolume = (int) (this.car.getGasolineVolume() - (this.request.getDistance() * 0.8));
		
		if (gasVolume <= 0) {
			this.car.setStatus(CarStatus.BROKEN);
			this.car.setGasolineVolume(0);
			
			this.crashInfo.append("У машины №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") кончился бензин!\n");
			this.crashInfo.setCaretPosition(this.crashInfo.getText().length());
			
			conditionCar.setText(" Нет бензина");
			conditionCar.setBackground(Color.RED);
			conditionCar.setForeground(Color.WHITE);
			
			while(this.car.getStatus() == CarStatus.BROKEN) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
			
			Budgeting.REPAIRS += this.car.getGasolineVolume() * 40;
			
			this.crashInfo.append("Машина №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") заправили!\n");
			conditionCar.setText(" Заявка №" + request.getId());
			conditionCar.setBackground(Color.GREEN);
			conditionCar.setForeground(Color.BLACK);
		}
		
		this.car.setGasolineVolume(gasVolume);
				
		try {
			sleep(50000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		
		Budgeting.INCOME += request.getPrice();
		
		conditionCar.setText(" Ожидает");
		conditionCar.setBackground(Color.YELLOW);
		
		car.setStatus(CarStatus.FREE);
		
		
			
		CarPark.FREECARS.add(car);
		CarPark.BUSYCARS.remove(car.getId());
		
		Budgeting.PROFIT = Budgeting.INCOME - Budgeting.REPAIRS;
		
		this.logInfo.append(" Машина " + car.getId() + " выполнила заявку №" + request.getId() + "\n");
		this.logInfo.setCaretPosition(this.logInfo.getText().length());
			
		//System.out.println("Машина " + car.getId() + " выполнила заявку №" + request.getId());
		
	}

}
