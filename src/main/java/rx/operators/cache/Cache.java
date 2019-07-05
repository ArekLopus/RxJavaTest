package rx.operators.cache;

import io.reactivex.Observable;

//-Do not use cache() unless you really want to hold all elements indefinitely and do not have plans to dispose it at any point.

//-Returns an Observable that subscribes to this ObservableSource lazily, caches all of its events and replays them,
// in the same order as received, to all the downstream subscribers.
//-cache does not operate by default on a particular Scheduler.
public class Cache {

	public Cache() {
		
		Observable<Integer> cachedRollingTotals = Observable.just(1, 2, 3, 4, 5)
			.doOnNext(e -> System.out.print("On next - "))
		    .scan(0, (total,next) -> total + next)
		    .skip(1)
		    .cache();
		
		cachedRollingTotals.subscribe(System.out::println);
		
		// Cached, does not call doOnNext()
		cachedRollingTotals.subscribe(System.out::println);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Cache();

	}

}
