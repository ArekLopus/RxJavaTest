package rx.operators.transforming;

import java.util.Arrays;

import io.reactivex.Observable;

//-Returns an Observable that emits a specified item(s) before it begins to emit items emitted by the sourceObservableSource. 
//-startWith does not operate by default on a particular Scheduler.
public class StartWith {

	public StartWith() {
		
		Observable<String> source = Observable.just("One", "Two");
		Observable<String> source2 = Observable.just("Three", "Four");
		
		source.startWith("Zero")
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		source.startWith(source2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		source.startWith(Arrays.asList("Three", "Four"))
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new StartWith();

	}

}
