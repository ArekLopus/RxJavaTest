package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that drops a specified number of items from the end of the sequence emitted by the source ObservableSource.  
//-This version of skipLast does not operate by default on a particular Scheduler.
public class SkipLast_value {

	public SkipLast_value() {
		
		Observable.just("One", "Two", "Three", "Four")
			.skipLast(2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new SkipLast_value();

	}

}
