package levrusha.com.github.thread;

import javax.swing.JTextArea;

import levrusha.com.github.enums.CarStatus;
import levrusha.com.github.model.Car;
import levrusha.com.github.model.Request;
import levrusha.com.github.storage.CarPark;

import static java.lang.Thread.sleep;

public class CarConditionTrackerThread implements Runnable {
	
	private Request request;
	private Car car;
	private boolean carCrashed;
	private JTextArea crashInfo;
	
	public CarConditionTrackerThread(Request request, Car car, boolean carCrashed, JTextArea crash) {
		this.request = request;
		this.car = car;
		this.carCrashed = carCrashed;
		this.crashInfo = crash;
	}

	@Override
	public void run() {
		if (carCrashed) {
			this.car.setStatus(CarStatus.BROKEN);
			crashInfo.append("Машина №" + this.car.getId() + " (" + this.car.getModel() + ", " + this.car.getplateLicense() + ") сломалась!\n");
			//System.out.println("Машина " + this.car.getId() + " сломалась!");
			
			while(this.car.getStatus() == CarStatus.BROKEN) {
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		car.setStatus(CarStatus.FREE);
			
		CarPark.FREECARS.add(car);
		CarPark.BUSYCARS.remove(car);		
			
		System.out.println("Машина " + car.getId() + " выполнила заявку №" + request.getId());
		
	}

}
