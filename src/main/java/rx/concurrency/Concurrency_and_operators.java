package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Something else that is exciting about RxJava is its operators (at least the official ones and the custom ones built properly).
// They can work with Observables on different threads safely.
//-Even operators and factories that combine multiple Observables, such as merge() and zip(), will safely combine emissions pushed
// by different threads. Fe, we can use zip() on two Observables, even if they are emitting on two separate computation threads.

//-Being able to split and combine Observables happening on different threads is powerful and eliminates the pain points of callbacks.
//-Observables are agnostic to whatever thread they work on, making concurrency easy to implement, configure, and evolve at any time.
public class Concurrency_and_operators {

	public Concurrency_and_operators() throws InterruptedException {
		
		Observable<String> source1 = Observable.just("One", "Two", "Three")
			.subscribeOn(Schedulers.computation())
			.map(s -> RxUtils.hardWork(s.length() * 500) + ", thread " + Thread.currentThread().getName());
		
		Observable<String> source2 = Observable.range(1,6)
			.subscribeOn(Schedulers.computation())
			.map(s -> RxUtils.hardWork(s * 500) + ", thread " + Thread.currentThread().getName());
		
		Observable.zip(source1, source2, (s1, s2) -> s1 + "   ---   " + s2)
	    	.subscribe(System.out::println);
		
		Thread.sleep(6000);		
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Concurrency_and_operators();

	}

}



