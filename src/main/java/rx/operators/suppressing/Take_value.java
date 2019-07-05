package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that emits only the first count items emitted by the source ObservableSource.
// If the source emits fewer than count items then all of its items are emitted. 
//-This version of take does not operate by default on a particular Scheduler.
public class Take_value {

	public Take_value() {
		
		Observable.just("One", "Two", "Three", "Four")
			.take(2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Take_value();

	}

}
