package rx.operators.combining;

import io.reactivex.Observable;

//-Flattens this and another ObservableSource into a single ObservableSource, without any transformation.
//-mergeWith does not operate by default on a particular Scheduler.
public class MergeArray {

	@SuppressWarnings("unchecked")
	public MergeArray() {
		
		Observable<String> source1 = Observable.just("One", "Two");
		Observable<String> source2 = Observable.just("Three", "Four");
		Observable<String> source3 = Observable.just("Five", "Six");
		Observable<String> source4 = Observable.just("Seven", "Eight");
		Observable<String> source5 = Observable.just("Nine", "Ten");
		
		Observable.mergeArray(source1, source2, source3, source4, source5)
			.subscribe(i -> System.out.println("Received: " + i));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new MergeArray();

	}

}
