package levrusha.com.github.model;

import levrusha.com.github.enums.CarStatus;

public class Car implements Comparable<Car> {
	
	private static int counter = 0;
	private int id;
	private CarStatus status;
	private String numberOfPlateLicense;
	
	public Car() {
		
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
		return "Тахи [ID =" + id + ", counter =" + Car.counter + ", status=" + status + ", numberOfPlateLicense=" + numberOfPlateLicense + "]";
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

	public String getNumberOfPlateLicense() {
		return numberOfPlateLicense;
	}

	public void setNumberOfPlateLicense(String numberOfPlateLicense) {
		this.numberOfPlateLicense = numberOfPlateLicense;
	}
}
