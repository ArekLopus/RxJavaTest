package rx.operators.reducing;

import io.reactivex.Observable;

//-Returns a Single that emits a Boolean that indicates whether the source ObservableSource emitted a specified item. 
//-contains does not operate by default on a particular Scheduler.
public class Contains {
	
	public Contains() {
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.contains(1)
			.subscribe(s -> System.out.println("Received: " + s));
		
		Observable.just(1, 2, 3, 4, 5, 6)
			.contains(10)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Contains();

	}

}
