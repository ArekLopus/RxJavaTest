package rx.operators.replay;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-The replay() operator is a way to hold onto previous emissions within a certain scope and re-emit them when a new Observer comes in.
//-It will return a ConnectableObservable that will both multicast emissions as well as emit previous emissions defined in a scope.
//-Previous emissions it caches will fire immediately to a new Observer so it is caught up,
// and then it will fire current emissions from that point forward.

//-Just note that this can get expensive with memory, as replay() will keep caching all emissions it receives.
//-If the source is infinite or you only care about the last previous emissions, you might want to specify
// a bufferSize argument to limit only replaying a certain number of last emissions.

//-Returns a ConnectableObservable that shares a single subscription to the underlying ObservableSourcethat will replay all of its items
// and notifications to any future Observer. A ConnectableObservableSource resembles an ordinary ObservableSource, except that
// it does not begin emitting items when it is subscribed to, but only when its connect method is called. 
//-This version of replay does not operate by default on a particular Scheduler.
public class Replay_ {

	public Replay_() throws InterruptedException {
		
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
		    .replay()
			//.publish()
		    .autoConnect();
		
		seconds.subscribe(l -> System.out.println("Observer 1: " + l));		//Observer 1
		Thread.sleep(3000);
		
		// After 3 seconds, Observer 2 comes in and immediately receives the first three emissions it missed.
		seconds.subscribe(l -> System.out.println("Observer 2: " + l));		//Observer 2
		Thread.sleep(3000);
			
			
		System.out.println("--- Main Thread Finished ---");
			
	}

	public static void main(String[] args) throws Exception {
		new Replay_();

	}

}
