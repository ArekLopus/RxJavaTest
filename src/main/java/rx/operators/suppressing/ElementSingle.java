package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns a Maybe that completes if this Observable is empty or emits the single item emitted by this Observable,
// or signals an IllegalArgumentException if this Observable emits more than one item.
//-singleElement does not operate by default on a particular Scheduler.
public class ElementSingle {

	public ElementSingle() {
		
		Observable.just("One")
			.singleElement()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println);
		
		Observable.empty()
			.singleElement()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println, () -> System.out.println("Finished"));
		
		Observable.just("One", "Two", "Three", "Four")
			.singleElement()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ElementSingle();

	}

}
