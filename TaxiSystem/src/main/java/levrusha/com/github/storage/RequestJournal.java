package levrusha.com.github.storage;

import java.util.SortedSet;
import java.util.TreeSet;

import levrusha.com.github.model.Request;

public class RequestJournal {

	public static SortedSet<Request> REQUESTS = new TreeSet<>();
	public static SortedSet<Request> ARCHIVE = new TreeSet<>();
	
}
