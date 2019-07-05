package rx.operators.combining;

import io.reactivex.Observable;

//-Flattens this and another ObservableSource into a single ObservableSource, without any transformation.
//-mergeWith does not operate by default on a particular Scheduler.
public class MergeWith {

	public MergeWith() {
		
		//DOES NOT work with different types!
		Observable<String> source1 = Observable.just("One", "Two");
		Observable<String> source2 = Observable.just("Three", "Four");
		
		source1.mergeWith(source2)
	    	.subscribe(i -> System.out.println("Received: " + i));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new MergeWith();

	}

}
