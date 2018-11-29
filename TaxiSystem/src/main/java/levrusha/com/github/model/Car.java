package levrusha.com.github.model;

import levrusha.com.github.enums.CarStatus;

public class Car implements Comparable<Car> {
	
	private static int counter = 0;
	private int id;
	private CarStatus status;
	private String plateLicense;
	private String model;
	private int gasolineVolume = 20;
	
	public Car() {
		
		setCounter();
		setId();
		setStatus(CarStatus.FREE);
		
	}
	
	@Override
	public int compareTo(Car car) {
		
		int result = getId() - car.getId();
		
		if (result!=0) {
			return result;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Тахи [ID = " + id + ", model = " + model + ", plateLicense = " + plateLicense+ ", status = " + status + "]";
	}

	public int getCounter() {
		return Car.counter;
	}

	public void setCounter() {
		++Car.counter;
	}
	
	public int getId() {
		return id;
	}

	public void setId() {
		this.id = Car.counter;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}

	public String getplateLicense() {
		return plateLicense;
	}

	public void setplateLicense(String plateLicense) {
		this.plateLicense = plateLicense;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public void setGasolineVolume(int volume) {
		this.gasolineVolume = volume;
	}
	
	public int getGasolineVolume() {
		return this.gasolineVolume;
	}
}
