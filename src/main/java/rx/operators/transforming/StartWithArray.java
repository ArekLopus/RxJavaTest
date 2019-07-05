package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that emits the specified items before it begins to emit items emitted by the sourceObservableSource.  
//-startWithArray does not operate by default on a particular Scheduler.
public class StartWithArray {

	public StartWithArray() {
		
		Observable<String> source = Observable.just("One", "Two");
		
		source.startWithArray("Three", "Four")
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new StartWithArray();

	}

}
