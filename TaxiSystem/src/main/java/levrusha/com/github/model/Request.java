package levrusha.com.github.model;

import java.util.Date;
import java.util.Random;

import levrusha.com.github.enums.RequestStatus;
import levrusha.com.github.storage.StreetsList;

public class Request implements Comparable<Request>{
	
	private static int counter = 0;
	private int id;
	
	private Date date;
	private RequestStatus status;
	
	private String fromStreet;
	private int fromHouse;
	
	private String toStreet;
	private int toHouse;
	
	private double distance;
	private int time;
	private int price;
	
	public Request() {
		
		setCounter();
		setId();
		
		this.status = RequestStatus.PENDING;
		this.date = new Date();
		
		this.fromStreet = generateFromStreet();
		this.fromHouse = new Random().nextInt(100 - 1) + 1;
		
		generateToStreet();
		this.toHouse = new Random().nextInt(100 - 1) + 1;
		
		this.distance = generateDistance();
		this.time = (int)(this.distance / 40 * 100);
		this.price = generatePrice();
		
	}
	
	
	@Override
	public String toString() {
		return "Заявка " + id + ". Откуда: " + fromStreet + " " + fromHouse + ". Куда: " + toStreet + " " + toHouse + ". Расстояние: " + distance + " км. Время: " + time + " мин. Стоимость: " + price + " руб.";
	}
	
	/**
	 * Я переопределил метод compareTo интерфейса Comparable, который работает с объектами Request.
	 * Это нужно для того, чтобы объекты этого типа можно было сравнивать
	 * (они же составные и нужно объяснить машине по каким свойствам сравнивать)
	 * Больше в интернете
	 */
	@Override
	public int compareTo(Request request) {
		
		int result = getId() - request.getId();
		
		if(result != 0) {
			return (int) result / Math.abs(result);
		}
		
		return 0;
	}
	
	private String generateFromStreet() {
		return StreetsList.streets.get(new Random().nextInt(StreetsList.streets.size()));
	}
	
	private void generateToStreet() {
		String toStreet = StreetsList.streets.get(new Random().nextInt(StreetsList.streets.size()));
		System.out.println("from = " + this.fromStreet + ", to = " + toStreet + ", toStreet == fromStreet ? " +  toStreet.equals(this.fromStreet));
		if (toStreet.equals(this.fromStreet)) {
			generateToStreet();
		}
		else {
			this.toStreet = toStreet;
		}
	}
	
	
	private double generateDistance() {
		int from = StreetsList.streets.indexOf(this.fromStreet);
		int to = StreetsList.streets.indexOf(this.toStreet);
		double distance = from - to;
		return Math.abs(distance);
	}
	
	@SuppressWarnings("deprecation")
	private int generatePrice() {
		
		Date start = new Date();
		Date end = new Date();
		
		int price = 0;
		
		start.setMinutes(50);
		end.setMinutes(59);
		
		if (this.date.after(start) && this.date.before(end)) {
			price = (int) ((this.distance * 6 + this.time * 4) * 1.5);
		}
		else {
			price = (int) (this.distance * 6 + this.time * 4);
		}
		
		return (price > 85) ? price : 85; 
	
	}
	

	public int getCounter() {
		return counter;
	}

	public void setCounter() {
		++Request.counter;
	}
	
	public void setId() {
		this.id = Request.counter;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public double getDistance() {
		return this.distance;
	}
}
