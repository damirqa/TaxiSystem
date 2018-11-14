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
	private String from;
	private String to;
	private double distance;
	private int time;
	
	public Request() {
		
		setCounter();
		setId();
		
		this.status = RequestStatus.PENDING;
		this.date = new Date();
		
		this.from = generatePlaceFrom();
		this.to = generatePlaceTo();
		
		this.distance = generateDistance();
		this.time = (int)(this.distance / 40 * 100);
		
	}
	
	
	@Override
	public String toString() {
		return "Заявка [ID=" + id + ", from = " + from + ", to = " + to + ", distance = " + distance + ", time = " + time + "]";
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}
	
	
	
	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public double getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	private String generatePlaceFrom() {
		int house = new Random().nextInt(100 - 1) + 1;
		return StreetsList.streets.get(new Random().nextInt(StreetsList.streets.size())) + " " + house;
	}
	
	private String generatePlaceTo() {
		int house = new Random().nextInt(100 - 1) + 1;
		String to = StreetsList.streets.get(new Random().nextInt(StreetsList.streets.size()));
		String[] fromandhouse = this.from.split(" ");
		String from = fromandhouse[0];
		System.out.println(from + " " + to);
		if (from.equals(to)) {
			generatePlaceTo();
		}
		return to + " " + house;
	}
	
	private double generateDistance() {
		int from = StreetsList.streets.indexOf(this.from.substring(0, this.from.length() -3 ));
		int to = StreetsList.streets.indexOf(this.to.substring(0, this.to.length() - 3));
		double distance = from - to;
		return Math.abs(distance);
	}
}
