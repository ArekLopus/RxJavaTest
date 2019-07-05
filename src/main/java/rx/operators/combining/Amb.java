package rx.operators.combining;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Ambiguous
//-This is helpful when you have multiple sources for the same data or events and you want the fastest one to win.

//-Mirrors the one ObservableSource in an Iterable of several ObservableSources that first either emits an item or sends a termination notification. 
//-amb does not operate by default on a particular Scheduler.
public class Amb {

	public Amb() throws InterruptedException {
		
		Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)			// emit every second
		    .take(2)
		    .map(l -> l + 1)
		    .map(l -> "Source1: " + l + " seconds");
		
		Observable<String> source2 = Observable.interval(500, TimeUnit.MILLISECONDS)	// emit every 300 ms
		    .map(l -> (l + 1) * 500)
		    .map(l -> "Source2: " + l + " milliseconds");
		
		Observable.amb(Arrays.asList(source1, source2))									//emit Obs that emits first
		    .subscribe(i -> System.out.println("RECEIVED: " + i));
		
		Thread.sleep(3000);	
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Amb();

	}

}
