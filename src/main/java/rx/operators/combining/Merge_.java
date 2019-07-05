package rx.operators.combining;

import io.reactivex.Observable;

//-Flattens two ObservableSources into a single ObservableSource, without any transformation.  
//-merge does not operate by default on a particular Scheduler.
public class Merge_ {

	public Merge_() {
		
		//Works with different types!
		Observable<String> source1 = Observable.just("One", "Two" , "Three", "Four");
		Observable<Integer> source2 = Observable.just(1, 2, 3, 4);
		
		Observable.merge(source1, source2)
	    	.subscribe(i -> System.out.println("Received: " + i));
		


		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Merge_();

	}

}
