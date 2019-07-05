package rx.flowable.create;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Up to 10 items.
//-just does not operate by default on a particular Scheduler.
//-BackpressureSupport - The operator fully supports backpressure and may coordinate downstream requests with upstream requests through batching,
// arbitration or by other means.
public class Flowable_just {

	public Flowable_just() throws InterruptedException {
		
		Flowable.just(1, 2, 3, 4)
			.doOnNext(i -> System.out.println("Emitting " + i))
			.observeOn(Schedulers.io())
			.subscribe(i -> {
				Thread.sleep(400);
				System.out.println("Received " + i);
			});
		
		Thread.sleep(2000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_just();

	}

}
