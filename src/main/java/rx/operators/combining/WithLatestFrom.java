package rx.operators.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-It will map each T emission with the latest values from other Observables and combine them, but it will only take one emission
// from each of the other Observables
//-You can pass up to four Observable instances of any varying types to withLatestFrom().

//-Merges the specified ObservableSource into this ObservableSource sequence by using the resultSelectorfunction
// only when the source ObservableSource (this instance) emits an item. 
//-withLatestFrom does not operate by default on a particular Scheduler.
public class WithLatestFrom {

	public WithLatestFrom() throws InterruptedException {
		
//		Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
		Observable<String> source1 = Observable.just("One", "Two", "Three", "Four");
		Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);
		
		source2.withLatestFrom(source1, (s2,s1) -> "Source 2: " + s2 + "    Source 1: " + s1)
		    .subscribe(System.out::println);
				
		Thread.sleep(4000);	
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new WithLatestFrom();

	}

}
