package rx.flowable.create;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Converts an arbitrary Reactive-Streams Publisher into a Flowable if not already a Flowable.

//-The Publisher must follow the Reactive-Streams specification.Violating the specification may result in undefined behavior. 
//-If possible, use create(FlowableOnSubscribe, BackpressureStrategy) to create asource-like Flowable instead. 
//-Note that even though Publisher appears to be a functional interface, itis not recommended to implement it through a lambda
// as the specification requiresstate management that is not achievable with a stateless lambda. 

//-BackpressureSupport - The backpressure-related requests pass through this operator without change.
//-fromIterable does not operate by default on a particular Scheduler.
public class Flowable_fromPublisher {

	public Flowable_fromPublisher() throws InterruptedException {
		
		Flowable<Integer> range = Flowable
			.range(0, 300);
		
		Flowable.fromPublisher(range)
		.doOnNext(i -> System.out.println("Emitting " + i))
		.observeOn(Schedulers.io())
		.subscribe(i -> {
			Thread.sleep(500);
			System.out.println("Received " + i);
		});
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_fromPublisher();

	}

}
