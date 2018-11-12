package levrusha.com.github.storage;

import java.util.SortedSet;
import java.util.TreeSet;

import levrusha.com.github.model.Car;

public class CarPark {

	public static SortedSet<Car> freeCars = new TreeSet<>();
	public static SortedSet<Car> busyCars = new TreeSet<>();
	public static SortedSet<Car> brokenCars = new TreeSet<>();
	public static SortedSet<Car> outCars = new TreeSet<>();
	
}
