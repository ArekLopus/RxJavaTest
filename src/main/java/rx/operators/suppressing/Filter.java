package rx.operators.suppressing;

import io.reactivex.Observable;

//-Filters items emitted by an ObservableSource by only emitting those that satisfy a specified predicate. 
//-filter does not operate by default on a particular Scheduler.
public class Filter {

	public Filter() {
		
		Observable.just("One", "Two", "Three", "Four")
			.filter(s -> s.startsWith("O"))
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Filter();

	}

}
