package rx.flowable.create;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


//-Flowable.defer() creates a separate state for each Flowable.
//-You can create a fresh Flowable for each subscription using Flowable.defer(),
// which accepts a lambda instructing how to create an Flowable for every subscription.

//-Returns a Flowable that calls a Publisher factory to create a Publisher for each new Subscriberthat subscribes.
// That is, for each subscriber, the actual Publisher that subscriber observes isdetermined by the factory function.
//-BackpressureSupport - The backpressure-related requests pass through this operator without change.
//-defer does not operate by default on a particular Scheduler.
public class Flowable_defer {
	
	private int start = 1;
	private int count = 5;
	
	public Flowable_defer() throws InterruptedException {
		
		Flowable<Integer> source = Flowable.defer(() -> Flowable.range(start, count));
		
		source
			.doOnNext(i -> System.out.println("Emitting " + i))
			.observeOn(Schedulers.io())
			.subscribe(i -> {
				Thread.sleep(100);
				System.out.println("Received " + i);
			});
		System.out.println();
		
		Thread.sleep(1000);
		System.out.println();
		
		//modify count
		count = 2;
		source
			.doOnNext(i -> System.out.println("Emitting " + i))
			.observeOn(Schedulers.io())
			.subscribe(i -> {
				Thread.sleep(100);
				System.out.println("Received " + i);
			});
		
		Thread.sleep(1000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Flowable_defer();

	}

}
