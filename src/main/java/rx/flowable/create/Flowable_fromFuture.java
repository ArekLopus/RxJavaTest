package rx.flowable.create;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-Converts a Future into a Publisher. 
//-You can convert any object that supports the Future interface into a Publisher that emits thereturn value
// of the Future.get() of that object by passing the object into the frommethod. 
//-Important note: This Publisher is blocking on the thread it gets subscribed on; you cannot cancel it. 
//-Unlike 1.x, canceling the Flowable won't cancel the future. If necessary, one can use composition to achieve
// the cancellation effect: futurePublisher.doOnCancel(() -> future.cancel(true));.

//-BackpressureSupport - The operator fully supports backpressure and may coordinate downstream requests with upstream requests through batching,
// arbitration or by other means.
//-fromArray does not operate by default on a particular Scheduler.
public class Flowable_fromFuture {

	public Flowable_fromFuture() throws InterruptedException {
		
		CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(() -> Observable.range(0, 300).toList().blockingGet()); 
		
		Flowable.fromFuture(cf)
			.flatMap(e -> Flowable.fromArray(e.toArray()))
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
		new Flowable_fromFuture();

	}

}
