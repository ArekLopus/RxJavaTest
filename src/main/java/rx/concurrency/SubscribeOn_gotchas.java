package rx.concurrency;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-It is important to note that subscribeOn() will have no practical effect with certain sources
// (and will keep a worker thread unnecessarily on standby until that operation terminates).
//-This might be because these Observables already use a specific Scheduler, and if you want to change it, you can provide a Scheduler
// as an argument. Fe, Observable.interval() will use Schedulers.computation() and will ignore any subscribeOn() you specify otherwise.
//-But you can provide a third argument to specify a different Scheduler to use.
public class SubscribeOn_gotchas {

	public SubscribeOn_gotchas() throws InterruptedException {
		
		Observable.interval(1, TimeUnit.SECONDS)
			.unsubscribeOn(Schedulers.newThread())							// DOES NOT work! (uses RxComputationThreadPool)
			.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		
		Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread())	// use Schedulers.newThread()
	    	.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new SubscribeOn_gotchas();

	}

}



