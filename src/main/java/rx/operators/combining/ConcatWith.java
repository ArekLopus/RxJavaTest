package rx.operators.combining;

import io.reactivex.Observable;

//-Returns an Observable that emits the items emitted from the current ObservableSource, then the next, one after the other,
// without interleaving them.
//-concatWith does not operate by default on a particular Scheduler.
public class ConcatWith {

	public ConcatWith() {
		
		//DOES NOT work with different types!
		Observable<String> source1 = Observable.just("One", "Two");
		Observable<String> source2 = Observable.just("Three", "Four");
		
		source1.concatWith(source2)
	    	.subscribe(i -> System.out.println("Received: " + i));
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ConcatWith();

	}

}
