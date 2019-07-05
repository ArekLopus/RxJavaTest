package rx.operators.collection;

import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.Observable;

//-Returns a Single that emits a single item, a list composed of all the items emitted by the finite source ObservableSource.
//-toList does not operate by default on a particular Scheduler.
public class ToList {
	
	public ToList() {
		
		Observable.just("One", "Two", "Three", "Four")
			.toList()
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		System.out.println();
		
		Observable.just("One", "Two", "Three", "Four")
			.toList(CopyOnWriteArrayList::new)
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		System.out.println();
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ToList();

	}

}
