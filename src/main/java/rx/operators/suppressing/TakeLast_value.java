package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that emits at most the last count items emitted by the source ObservableSource.
// If the source emits fewer than count items then all of its items are emitted. 
//-This version of take does not operate by default on a particular Scheduler.
public class TakeLast_value {

	public TakeLast_value() {
		
		Observable.just("One", "Two", "Three", "Four")
			.takeLast(2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new TakeLast_value();

	}

}
