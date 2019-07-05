package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that skips the first count items emitted by the source ObservableSource and emits the remainder. 
//-This version of skip does not operate by default on a particular Scheduler.
public class Skip_value {

	public Skip_value() {
		
		Observable.just("One", "Two", "Three", "Four")
			.skip(2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println("First Observabele Done");
		
		Observable.just("One", "Two", "Three", "Four")
			.skip(5)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Skip_value();

	}

}
