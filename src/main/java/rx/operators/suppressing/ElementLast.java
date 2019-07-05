package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns a Maybe that emits the last item emitted by this Observable or completes if this Observable is empty.
//-lastElement does not operate by default on a particular Scheduler.
public class ElementLast {

	public ElementLast() {
		
		Observable.just("One", "Two", "Three", "Four")
			.lastElement()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()), System.out::println);
		
		Observable.empty()
			.lastElement()
			.subscribe(System.out::println, System.out::println, () -> System.out.println("Finished"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ElementLast();

	}

}
