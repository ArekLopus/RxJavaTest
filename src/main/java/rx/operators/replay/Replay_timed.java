package rx.operators.replay;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-When Observer 2 comes in, it immediately receives emissions that happened in the last second, which were 1500 and 1800

//-You can also specify a bufferSize argument on top of a time interval,
// so only a certain number of last emissions are buffered within the time period. 

//-Returns a ConnectableObservable that shares a single subscription to the source ObservableSource and replays all items emitted by that ObservableSource
// within a specified time window. 
public class Replay_timed {

	public Replay_timed() throws InterruptedException {
		
		Observable<Long> seconds = Observable.interval(500, TimeUnit.MILLISECONDS)
			.map(l -> (l + 1) * 500)
			.replay(1, TimeUnit.SECONDS)
			//.replay(1, 1, TimeUnit.SECONDS)
			.autoConnect();
		
		seconds.subscribe(l -> System.out.println("Observer 1: " + l));	//Observer 1
		Thread.sleep(2000);
		
		seconds.subscribe(l -> System.out.println("Observer 2: " + l));	//Observer 2
		Thread.sleep(1000);
		System.out.println();
		
		
		System.out.println("--- Main Thread Finished ---");
			
	}

	public static void main(String[] args) throws Exception {
		new Replay_timed();

	}

}
