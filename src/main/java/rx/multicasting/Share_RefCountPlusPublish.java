package rx.multicasting;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-You can use an alias for publish().refCount() using the share() operator.

//-Returns a new ObservableSource that multicasts (and shares a single subscription to) the original ObservableSource.
// As long asthere is at least one Observer this ObservableSource will be subscribed and emitting data.
// When all subscribers have disposed it will dispose the source ObservableSource. 
//-This refCount overload does not operate on any particular Scheduler.
public class Share_RefCountPlusPublish {

	public Share_RefCountPlusPublish() throws InterruptedException {
		
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
			.share();
		    //.publish().refCount();
		
		seconds.take(5)													//Observer 1
		    .subscribe(l -> System.out.println("Observer 1: " + l));
		Thread.sleep(3000);
		
		seconds.take(2)													//Observer 2
		    .subscribe(l -> System.out.println("Observer 2: " + l));
		
		Thread.sleep(3000);
		
		// should be no more Observers at this point
		seconds.subscribe(l -> System.out.println("Observer 3: " + l));	// Observer 3
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Share_RefCountPlusPublish();

	}

}
