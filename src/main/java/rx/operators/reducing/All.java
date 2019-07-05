package rx.operators.reducing;

import io.reactivex.Observable;

//-Returns a Single that emits a Boolean that indicates whether all of the items emitted by the sourceObservableSource satisfy a condition.   
//-all does not operate by default on a particular Scheduler.
public class All {
	
	public All() {
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.all(i -> i < 10)
			.subscribe(s -> System.out.println("Received: " + s));
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.all(i -> i < 5)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new All();

	}

}
