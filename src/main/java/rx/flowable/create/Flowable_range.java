package rx.flowable.create;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Returns a Flowable that emits a sequence of Integers within a specified range. 
//-The two arguments for Flowable.range() are not lower/upper bounds.
// The first argument is the starting value. The second argument is the total count of emissions,

//-There is Flowable.rangeLong() if you need to emit larger numbers.

//-BackpressureSupport - The operator fully supports backpressure and may coordinate downstream requests with upstream requests through batching,
// arbitration or by other means.
//-Does not operate by default on a particular Scheduler. 
public class Flowable_range {

	public Flowable_range() throws InterruptedException {
		
		Flowable.range(1, 200)
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
		new Flowable_range();

	}

}
