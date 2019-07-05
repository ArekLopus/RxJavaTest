package rx.operators.combining;

import io.reactivex.Observable;

//-Returns an Observable that emits the results of a specified combiner function applied to combinations of items emitted,
// in sequence, by an array of other ObservableSources. 
//-zipArray does not operate by default on a particular Scheduler.
public class ZipArray {

	@SuppressWarnings("unchecked")
	public ZipArray() throws InterruptedException {
		
		Observable<String> source1 = Observable.just("One", "Two", "Three", "Four", "Five");
		Observable<Integer> source2 = Observable.range(1,6); 
		
		Observable.zipArray( s -> s[0] + "-" + s[1], true, 1, source1, source2 )
			.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new ZipArray();

	}

}
