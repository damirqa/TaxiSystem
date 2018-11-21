package levrusha.com.github.thread;

import javax.swing.JTextArea;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.Budgeting;
import levrusha.com.github.storage.CarPark;

import static java.lang.Thread.sleep;

public class CarConditionTrackerThread implements Runnable {
	
	private Request request;
	private Car car;
	private boolean carCrashed;
	private JTextArea crashInfo;
	private JTextArea logInfo;
	
	public CarConditionTrackerThread(Request request, Car car, boolean carCrashed, JTextArea crash, JTextArea log) {
		this.request = request;
		this.car = car;
		this.carCrashed = carCrashed;
		this.crashInfo = crash;
		this.logInfo = log;
	}

	@Override
	public void run() {
		if (carCrashed) {
			this.car.setStatus(CarStatus.BROKEN);
			
			this.crashInfo.append("Машина №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") сломалась!\n");
			this.crashInfo.setCaretPosition(this.logInfo.getText().length());
			
			
			//System.out.println("Машина " + this.car.getId() + " сломалась!");
			
			while(this.car.getStatus() == CarStatus.BROKEN) {
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Budgeting.REPAIRS += 500;
			
			this.crashInfo.append("Машина №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") почининили!\n");
		}
				
		try {
			sleep(50000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		
		Budgeting.INCOME += request.getPrice();
		
		car.setStatus(CarStatus.FREE);
			
		CarPark.FREECARS.add(car);
		CarPark.BUSYCARS.remove(car.getId());
		
		Budgeting.PROFIT = Budgeting.INCOME - Budgeting.REPAIRS;
		
		this.logInfo.append(" Машина " + car.getId() + " выполнила заявку №" + request.getId() + "\n");
		this.logInfo.setCaretPosition(this.logInfo.getText().length());
			
		//System.out.println("Машина " + car.getId() + " выполнила заявку №" + request.getId());
		
	}

}
