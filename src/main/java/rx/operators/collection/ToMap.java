package rx.operators.collection;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;

//-Returns a Single that emits a single HashMap containing all items emitted by the finite source ObservableSource,
// mapped by the keys returned by a specified keySelector function. 
//-toMap does not operate by default on a particular Scheduler.
public class ToMap {
	
	public ToMap() {
		
		Observable.just("One", "Two", "Three", "Four")
			.toMap(s -> s.length())
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		
		Observable.just("One", "Two", "Three", "Four")
			.toMap(s -> s, s -> s.length())
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		
		Observable.just("One", "Two", "Three", "Four")
			.toMap(s -> s, s -> s.length(), ConcurrentHashMap::new)
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ToMap();

	}

}
