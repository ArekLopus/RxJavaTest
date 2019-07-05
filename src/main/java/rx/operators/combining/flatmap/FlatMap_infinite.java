package rx.operators.combining.flatmap;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-We emit simple Integer values from Observable<Integer> and use flatMap() on them to drive an Observable.interval(), where each one serves
// as the period argument. In the following code snippet, we emit the values 2, 3, 10, and 7, which will yield interval Observables that
// emit at 2 seconds, 3 seconds, 10 seconds, and 7 seconds, respectively. These four Observables will be merged into a single stream.
public class FlatMap_infinite {

	public FlatMap_infinite() throws InterruptedException {
		//
		Observable<Integer> intervalArguments = Observable.just(2, 3, 10, 7);
		intervalArguments.flatMap( i -> Observable
											.interval(i, TimeUnit.SECONDS)
											.map(iv -> i + "s interval: " + ((iv + 1) * i) + " seconds elapsed")
		)
		.subscribe(System.out::println);
		
		
		Thread.sleep(12000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new FlatMap_infinite();

	}

}
