package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that emits the items emitted by the source ObservableSource, converted to the specified type. 
//-cast does not operate by default on a particular Scheduler.
public class Cast {

	public Cast() {
		
		Observable.just("1918-11-11", "1941-12-07", "1945-05-07")
			.cast(Object.class)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Cast();

	}

}
