package rx.operators.suppressing;

import io.reactivex.Observable;

//-Returns an Observable that emits all items emitted by the source ObservableSource that are distinct
// from their immediate predecessors based on Object.equals(Object) comparison.  
//-distinct does not operate by default on a particular Scheduler.
public class DistinctUntillChanged {

	public DistinctUntillChanged() {
		
		Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
			.distinctUntilChanged()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new DistinctUntillChanged();

	}

}
