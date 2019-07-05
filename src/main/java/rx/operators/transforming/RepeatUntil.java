package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that repeats the sequence of items emitted by the source ObservableSource until the provided stop function returns true. 
//-repeatUntil does not operate by default on a particular Scheduler.
public class RepeatUntil {
	
	boolean test = false;
	int counter = 0;
	
	public RepeatUntil() {
		
		Observable.just("One", "Two", "Three")
			.repeatUntil( () -> {
				counter++;
				if(counter == 3)
					test = true;
				return test;
			})
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new RepeatUntil();

	}

}
