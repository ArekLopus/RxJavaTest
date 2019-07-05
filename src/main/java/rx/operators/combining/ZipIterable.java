package rx.operators.combining;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

//-Returns an Observable that emits the results of a specified combiner function applied to combinations of items emitted,
// in sequence, by an Iterable of other ObservableSources. 
//-zipIterable does not operate by default on a particular Scheduler.
public class ZipIterable {

	public ZipIterable() throws InterruptedException {
		
		Observable<String> source1 = Observable.just("One", "Two", "Three", "Four", "Five");
		Observable<Integer> source2 = Observable.range(1,6); 
		
		List<Observable<? extends Object>> list = Arrays.asList(source1, source2);
		
		Observable.zipIterable(list, s -> s[0] + "-" + s[1], true, 1)
			.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new ZipIterable();

	}

}
