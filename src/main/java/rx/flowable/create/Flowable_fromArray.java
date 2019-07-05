package rx.flowable.create;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-Converts an Array into a Publisher that emits the items in the Array.
//-BackpressureSupport - The operator fully supports backpressure and may coordinate downstream requests with upstream requests through batching,
// arbitration or by other means.
//-fromArray does not operate by default on a particular Scheduler.
public class Flowable_fromArray {

	public Flowable_fromArray() throws InterruptedException {
		
		List<Integer> list = Observable
			.range(0, 300)
			.toList()
			.blockingGet();
			
		Flowable.fromArray(list.toArray())
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
		new Flowable_fromArray();

	}

}
