package rx.operators.transforming;

import io.reactivex.Observable;

//-Returns an Observable that emits the items emitted by the source ObservableSource or the items of an alternateObservableSource
// if the source ObservableSource is empty. 
//-switchIfEmpty does not operate by default on a particular Scheduler.
public class SwitchIfEmpty {

	public SwitchIfEmpty() {
		
		Observable<String> source = Observable.just("One", "Two");
		
		Observable.empty()
			.switchIfEmpty(source)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		Observable.just("One")
			.switchIfEmpty(source)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new SwitchIfEmpty();

	}

}
