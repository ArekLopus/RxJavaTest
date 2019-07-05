package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns a Maybe that emits only the very first item emitted by the source ObservableSource,
// or completes if the source ObservableSource is empty.
//-firstElement does not operate by default on a particular Scheduler.
public class ElementFirst {

	public ElementFirst() {
		
		Observable.just("One", "Two", "Three", "Four")
			.firstElement()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println);
		
		Observable.empty()
			.firstElement()
			.subscribe(System.out::println, System.out::println, () -> System.out.println("Finished"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ElementFirst();

	}

}
