package rx.operators.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-When ANY source fires, it couples with the latest emissions from the others
//-It is especially helpful in combining UI inputs, as previous user inputs are frequently irrelevant and only the latest is of concern. 

//-It is similar to zip(), but for every emission that fires from one of the sources, it will immediately couple up with the latest emission
// from every other source. It will not queue up unpaired emissions for each source, but rather cache and pair the latest one.

//-Combines two source ObservableSources by emitting an item that aggregates the latest values of each of the source ObservableSources
// each time an item is received from either of the source ObservableSources, where this aggregation is defined by a specified function.  
//-combineLatest does not operate by default on a particular Scheduler.
public class CombineLatest {

	public CombineLatest() throws InterruptedException {
		
		Observable<Long> source1 = Observable.interval(600, TimeUnit.MILLISECONDS);
		Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);
		
		Observable.combineLatest(source1, source2, (l1,l2) -> "Source 1: " + l1 + "    Source 2: " + l2)
		    .subscribe(System.out::println);
		
		Thread.sleep(4000);	
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new CombineLatest();

	}

}
