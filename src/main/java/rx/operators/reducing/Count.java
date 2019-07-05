package rx.operators.reducing;

import io.reactivex.Observable;

//-Returns a Single that counts the total number of items emitted by the source ObservableSource and emits this count as a 64-bit Long.  
//-count does not operate by default on a particular Scheduler.
public class Count {
	
	public Count() {
		
		Observable.just("One", "Two", "Three", "Four")
			.count()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Count();

	}

}
