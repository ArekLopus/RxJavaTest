package rx.operators.suppressing;

import io.reactivex.Observable;

//-You can get a specific emission by its index specified by a Long, starting at 0.
// After that item is found and emitted, onComplete() will be called and the subscription will be disposed of.

//-Returns a Maybe that emits the single item at a specified index in a sequence of emissions from this Observable
// or completes if this Observable signals fewer elements than index.  
//-elementAt does not operate by default on a particular Scheduler.
public class ElementAt {

	public ElementAt() {
		
		Observable.just("One", "Two", "Three", "Four")
			.elementAt(1)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Observable.just("One", "Two", "Three", "Four")
			.elementAt(5, "Default Item")
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ElementAt();

	}

}
