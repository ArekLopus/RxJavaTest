package rx.operators.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-This is helpful when you have multiple sources for the same data or events and you want the fastest one to win.

//-Mirrors the one ObservableSource in an array of several ObservableSources that first either emits an item or sendsa termination notification.
//-ambArray does not operate by default on a particular Scheduler.
public class AmbArray {

	@SuppressWarnings("unchecked")
	public AmbArray() throws InterruptedException {
		
		Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)			// emit every second
		    .take(2)
		    .map(l -> l + 1)
		    .map(l -> "Source1: " + l + " seconds");
		
		Observable<String> source2 = Observable.interval(500, TimeUnit.MILLISECONDS)	// emit every 500 ms
		    .map(l -> (l + 1) * 500)
		    .map(l -> "Source2: " + l + " milliseconds");
		
		Observable.ambArray(source1, source2)
			.subscribe(i -> System.out.println("Received: " + i));
		
		Thread.sleep(3000);	
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new AmbArray();

	}

}
