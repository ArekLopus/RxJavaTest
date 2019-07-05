package rx.operators.combining;

import io.reactivex.Observable;

//-Concatenates a variable number of ObservableSource sources. 
//-Note: named this way because of overload conflict with concat(ObservableSource<ObservableSource>)
//-concatArray does not operate by default on a particular Scheduler.
public class ConcatArray {

	@SuppressWarnings("unchecked")
	public ConcatArray() {
		
		Observable<String> source1 = Observable.just("One", "Two");
		Observable<String> source2 = Observable.just("Three", "Four");
		Observable<String> source3 = Observable.just("Five", "Six");
		Observable<String> source4 = Observable.just("Seven", "Eight");
		Observable<String> source5 = Observable.just("Nine", "Ten");
		
		Observable.concatArray(source1, source2, source3, source4, source5)
			.subscribe(i -> System.out.println("Received: " + i));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ConcatArray();

	}

}
