package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-if you have multiple subscribeOn() calls on a given Observable chain, the top-most one, or the one closest to the source, will win
// and cause any subsequent ones to have no practical effect (other than unnecessary resource usage).
//-If I call subscribeOn() with Schedulers.computation() and then call subscribeOn() for Schedulers.io(),
// Schedulers.computation() is the one that will be used.

//-This can happen when an API returns an Observable already preapplied with a Scheduler via subscribeOn(),
// although the consumer of the API wants a different Scheduler.
//-API designers are, therefore, encouraged to provide methods or overloads that allow parameterizing which Scheduler to use,
// just like RxJava's Scheduler-dependent operators (for example, Observable.interval()).
public class SubscribeOn_gotchas2 {

	public SubscribeOn_gotchas2() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.subscribeOn(Schedulers.computation())
	    	.filter(s -> s.length() > 2)
	    	.subscribeOn(Schedulers.io())
	    	.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new SubscribeOn_gotchas2();

	}

}



