package rx.operators.combining;

import io.reactivex.Observable;

//-Returns an Observable that emits items that are the result of applying a specified function to pairs of values,
// one each from the source ObservableSource and another specified ObservableSource.  
//-zipWith does not operate by default on a particular Scheduler.
public class ZipWith {

	public ZipWith() throws InterruptedException {
		
		Observable<String> source1 = Observable.just("One", "Two", "Three", "Four", "Five");
		Observable<Integer> source2 = Observable.range(1,6); 
		
		source1.zipWith(source2, (s1, s2) -> s1 + "-" + s2)
		    .subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new ZipWith();

	}

}
