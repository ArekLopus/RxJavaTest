package rx.multicasting;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-The refCount() operator on ConnectableObservable is similar to autoConnect(1), which fires after getting one subscription.
//-But there is one important difference; when it has no Observers anymore, it will dispose of itself and start over when a new one comes in.
//-It doesnt persist the subscription to the source when it has no more Observers, when another Observer follows, it will essentially "start over".

//-Returns an Observable that stays connected to this ConnectableObservable as long as there is at least one subscr to this ConnectableObservable.
//-This refCount overload does not operate on any particular Scheduler.
public class RefCount {

	public RefCount() throws InterruptedException {
		
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
		    .publish()
		    .refCount();
		
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
		new RefCount();

	}

}
