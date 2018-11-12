package levrusha.com.github.model;

import java.util.Date;

import levrusha.com.github.enums.RequestStatus;

public class Request implements Comparable<Request>{
	
	private static int counter = 0;
	private int id;
	private Date date;
	private RequestStatus status;
	
	public Request() {
		
	}
	
	
	@Override
	public String toString() {
		return "Заявка [ID=" + id + ", counter =" + Request.counter + ", Дата=" + date + ", статус=" + status + "]";
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
}
