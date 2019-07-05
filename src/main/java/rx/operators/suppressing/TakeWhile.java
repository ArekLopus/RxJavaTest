package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that emits items emitted by the source ObservableSource so long as each item satisfied a specified condition,
// and then completes as soon as this condition is not satisfied. 
//-takeWhile does not operate by default on a particular Scheduler.
public class TakeWhile {

	public TakeWhile() {
		
		Observable.just("One", "Two", "Three", "Four")
			.takeWhile(e -> e.length() < 4)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new TakeWhile();

	}

}
