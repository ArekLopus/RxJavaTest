package rx.operators.reducing;

import io.reactivex.Observable;

//-Returns a Single that emits true if any item emitted by the source ObservableSource satisfies as pecified condition, otherwise false.
// Note: this always emits false if the source ObservableSource is empty.
//-any does not operate by default on a particular Scheduler.
public class Any {
	
	public Any() {
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.any(i -> i < 10)
			.subscribe(s -> System.out.println("Received: " + s));
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.any(i -> i < 5)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Any();

	}

}
