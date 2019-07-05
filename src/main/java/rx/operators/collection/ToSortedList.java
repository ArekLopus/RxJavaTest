package rx.operators.collection;

import java.util.Comparator;

import io.reactivex.Observable;

//-Returns a Single that emits a list that contains the items emitted by the finite source ObservableSource, in a sorted order.
// Each item emitted by the ObservableSource must implement Comparable with respect to all other items in the sequence. 
//-toSortedList does not operate by default on a particular Scheduler.
public class ToSortedList {
	
	public ToSortedList() {
		
		Observable.just("One", "Two", "Three", "Four")
			.toSortedList()
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		System.out.println();
		
		Observable.just("One", "Two", "Three", "Four")
			.toSortedList(Comparator.reverseOrder())
			.subscribe(s -> System.out.println("Received: " + s + ", class: " + s.getClass()));
		System.out.println();
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ToSortedList();

	}

}
