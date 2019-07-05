package rx.operators.transforming;

import java.util.Comparator;

import io.reactivex.Observable;

//-Returns an Observable that emits the events emitted by source ObservableSource, in asorted order.
// Each item emitted by the ObservableSource must implement Comparable with respect to all other items in the sequence. 
//-sorted does not operate by default on a particular Scheduler.
public class Sorted {

	public Sorted() {
		
		Observable<Integer> source = Observable.just(5, 3, 2, 4, 1);
		
		source
			.sorted()
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		source
			.sorted( (e1, e2) -> e2 - e1 )
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		System.out.println();
		
		source
			.sorted(Comparator.reverseOrder())
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Sorted();

	}

}
