package rx.operators.combining.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-We can use operators to manipulate Observable inside flatMap().
public class FlatMap_manipulate_ {

	public FlatMap_manipulate_() throws InterruptedException {
		
		
		List<String> list = Arrays.asList("One", "Two", "Three", "Four");
		//Observable<Integer> range = Observable.range(1, 5);
		Observable<Long> range = Observable.interval(1, TimeUnit.NANOSECONDS);
		
		Observable.fromIterable(list)
			.flatMap(e -> Observable
								.just(e)
								.map(s -> s.length() + " - " + e))
								.zipWith(range, (s1, s2) -> (s2 + 1) + ". " + s1)
			.subscribe(i -> System.out.println(i));
		System.out.println();
		
		Thread.sleep(100);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new FlatMap_manipulate_();

	}

}
