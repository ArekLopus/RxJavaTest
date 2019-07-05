package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-If subscribeOn() is the only concurrent operation in an Observable chain, the thread from that Scheduler
// will work the entire Observable chain, pushing emissions from the source all the way to the final Observer. 
//-The subscribeOn() operator will suggest to the source Observable upstream which Scheduler to use and how to execute operations
// on one of its threads.
//-If that source is not already tied to a particular Scheduler, it will use the Scheduler you specify.
// It will then push emissions all the way to the final Observer using that thread (unless you add observeOn() calls).
//-You can put subscribeOn() anywhere in the Observable chain, and it will suggest to the upstream all the way
// to the origin Observable which thread to execute emissions with.

//-if you have multiple subscribeOn() calls on a given Observable chain, the top-most one, or the one closest to the source, will win
// and cause any subsequent ones to have no practical effect (other than unnecessary resource usage).
//-If I call subscribeOn() with Schedulers.computation() and then call subscribeOn() for Schedulers.io(),
// Schedulers.computation() is the one that will be used.
public class SubscribeOn_ {

	public SubscribeOn_() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")				//All 3 accomplish the same 
	    	.subscribeOn(Schedulers.computation())			//preferred
	    	.map(String::length)
	    	.filter(i -> i > 3)
	    	.subscribe(System.out::println);
		
		Observable.just("One", "Two", "Three")
	    	.map(String::length)
	    	.subscribeOn(Schedulers.computation())
	    	.filter(i -> i > 3)
	    	.subscribe(System.out::println);
		
		Observable.just("One", "Two", "Three")
	    	.map(String::length)
	    	.filter(i -> i > 3)
	    	.subscribeOn(Schedulers.computation())
	    	.subscribe(System.out::println);
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new SubscribeOn_();

	}

}



