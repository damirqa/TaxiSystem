package levrusha.com.github.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import levrusha.com.github.model.Car;

public class CarPark {

	public static HashMap<Integer, Car> BUSYCARS = new HashMap<>();
	public static SortedSet<Car> FREECARS = new TreeSet<>();
	public static ArrayList<Car> CARS = new ArrayList<>();
	
}
