package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that emits the items emitted by the source ObservableSource or a specified default item
// if the source ObservableSource is empty. 
//-defaultIfEmpty does not operate by default on a particular Scheduler.
public class DefaultEmpty {

	public DefaultEmpty() {
		
		Observable.empty()
			.defaultIfEmpty("Default empty")
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Observable.just("One")
			.defaultIfEmpty("Default empty")
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DefaultEmpty();

	}

}
