package rx.flowable.create;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.flowable.subscriber.SubscriberImpl;

//-Create a Flowable feels much like Observable.create(), but there is one critical difference;
// you must specify a BackpressureStrategy as a second argument.
//-This enumerable type does not by any means provide magic implementations of backpressure support.
// This simply supports backpressure by caching or dropping emissions or not implementing backpressure at all.
public class Flowable_create2 {

	public Flowable_create2() throws InterruptedException {
		
		Flowable<Integer> source = Flowable.create(emitter -> {
		    for (int i=0; i<=1000; i++) {
		        if (emitter.isCancelled())
		            return;
		        System.out.println("Emitted: " + i);
		        emitter.onNext(i + 1);
		    }
		    emitter.onComplete();
		}, BackpressureStrategy.LATEST);

		source
			.observeOn(Schedulers.io())
//		    .subscribe(e -> {
//		    	Thread.sleep(1000);
//		    	System.out.println(e);
//		    }, System.out::println, () -> System.out.println("Done"));
			.subscribe(new SubscriberImpl<Integer>(20, 10, 100));
			
		Thread.sleep(10000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new Flowable_create2();

	}

}
