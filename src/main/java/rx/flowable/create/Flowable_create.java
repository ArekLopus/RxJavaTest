package rx.flowable.create;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Create a Flowable feels much like Observable.create(), but there is one critical difference;
// you must specify a BackpressureStrategy as a second argument.
//-This enumerable type does not by any means provide magic implementations of backpressure support.
// This simply supports backpressure by caching or dropping emissions or not implementing backpressure at all.
public class Flowable_create {

	public Flowable_create() {
		
		Flowable.<String>create(emitter -> {
			try {
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					if(i % 10000000 == 0)
						System.out.println("Constructing Item " + i);
					emitter.onNext("Item " + i);
				}
				emitter.onComplete();
			} catch (Exception e) {
				emitter.onError(e);
			}
		}, BackpressureStrategy.DROP)
			.observeOn(Schedulers.io())
			.subscribe(i -> {
				Thread.sleep(1000);
				System.out.println("Received " + i);
			});
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Flowable_create();

	}

}
