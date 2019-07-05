package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Having multiple Observers to the same Observable with subscribeOn() will result in each one getting its own thread
// (or have them waiting for an available thread if none are available).
//-In the Observer, you can print the executing thread's name by calling Thread.currentThread().getName().
//-If we want only 1 thread to serve both Observers, we can multicast this operation.
// Just make sure subscribeOn() is before the multicast operators.
public class SubscribeOn_threads {

	public SubscribeOn_threads() throws InterruptedException {
		
		Observable<Long> lengths = Observable.just("One", "Four", "Three")
			    .subscribeOn(Schedulers.computation())
			    .map(String::length)
			    .map(e -> RxUtils.hardWork(e * 100));
			    
		lengths.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		lengths.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(3000);
		System.out.println();
		
		
		Observable<Long> lengths2 = Observable.just("One", "Four", "Three")
			.subscribeOn(Schedulers.computation())
		    .map(String::length)
		    .map(e -> RxUtils.hardWork(e * 100))
			.publish()
			.autoConnect(2);
		
		lengths2.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		lengths2.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(10000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new SubscribeOn_threads();

	}

}



