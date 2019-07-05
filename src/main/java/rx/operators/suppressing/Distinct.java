package rx.operators.suppressing;

import io.reactivex.Observable;

//-Emits each unique emission,
//-Returns an Observable that emits all items emitted by the source ObservableSource that are distinct based on Object.equals(Object) comparison. 
//-distinct does not operate by default on a particular Scheduler.
public class Distinct {

	public Distinct() {
		
		Observable.just("One", "Two", "Three", "Four")
			.distinct(String::length)
			.subscribe(s -> System.out.println(s + ", tread: " + Thread.currentThread().getName()));
		
		
		Observable.just("One", "Two", "Three", "Four")
			.map(String::length)
			.distinct()
			.sorted()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Distinct();

	}

}
