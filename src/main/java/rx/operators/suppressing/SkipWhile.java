package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that skips all items emitted by the source ObservableSource as long as a specified condition holds true,
// but emits all further source items as soon as the condition becomes false. 
//-skipWhile does not operate by default on a particular Scheduler.
public class SkipWhile {

	public SkipWhile() {
		
		Observable.just("One", "Two", "Three", "Four")
			.skipWhile(e -> e.length() < 4)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new SkipWhile();

	}

}
