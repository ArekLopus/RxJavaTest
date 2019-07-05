package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns a Single that emits the item found at a specified index in a sequence of emissions from this Observable
// or signals a NoSuchElementException if this Observable signals fewer elements than index. 
//-elementAtOrError does not operate by default on a particular Scheduler.
public class ElementAtOrError {

	public ElementAtOrError() {
		
		Observable.just("One", "Two", "Three", "Four")
			.elementAtOrError(2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println);
		
		Observable.just("One", "Two", "Three", "Four")
			.elementAtOrError(5)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ElementAtOrError();

	}

}
