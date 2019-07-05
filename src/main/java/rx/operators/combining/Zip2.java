package rx.operators.combining;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Zipping can also be helpful in slowing down emissions using Observable.interval(). 

//-Returns an Observable that emits the results of a specified combiner function applied to combinations of two items emitted,
// in sequence, by two other ObservableSources.  
//-zip does not operate by default on a particular Scheduler.
public class Zip2 {

	public Zip2() throws InterruptedException {
		
		Observable<String> source1 = Observable.just("One", "Two", "Three", "Four", "Five");
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
		Observable.zip(source1, seconds, (s, sec) -> s)
		    .subscribe(s -> System.out.println("Received " + s + " at " + LocalTime.now()));
		Thread.sleep(6000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Zip2();

	}

}
