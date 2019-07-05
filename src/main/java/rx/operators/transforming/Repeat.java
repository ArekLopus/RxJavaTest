package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that repeats the sequence of items emitted by the source ObservableSource at most count times.
//-repeat does not operate by default on a particular Scheduler.
public class Repeat {

	public Repeat() {
		
		Observable.just("One", "Two", "Three")
			//.repeat()		// repeats indefinitely.
			.repeat(2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Repeat();

	}

}
