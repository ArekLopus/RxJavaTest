package rx.operators.collection;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;

//-Returns a Single that emits a single HashMap that contains an ArrayList of items emitted by the finite source ObservableSource
// keyed by a specified keySelector function. 
//-toMultiMap does not operate by default on a particular Scheduler.
public class ToMultiMap {
	
	public ToMultiMap() {
		
		Observable.just("One", "Two", "Three", "Four")
			.toMultimap(s -> s.length())
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		
		Observable.just("One", "Two", "Three", "Four")
			.toMultimap(s -> s.length(), s -> s, ConcurrentHashMap::new)
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ToMultiMap();

	}

}
