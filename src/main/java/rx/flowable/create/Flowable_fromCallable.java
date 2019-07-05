package rx.flowable.create;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-Returns a Flowable that, when a Subscriber subscribes to it, invokes a function you specify
// and then emits the value returned from that function. 

//-This allows you to defer the execution of the function you specify until a Subscriber subscribes to the Publisher.
// That is to say, it makes the function "lazy." 

//-Also, if that procedure throws an error, we want it to be emitted up the Flowable chain through onError()
// rather than throw the error at that location in traditional Java fashion. (Callable throws an exception -> V call() throws Exception)
//-If initializing your emission has a likelihood of throwing an error, you should use Flowable.fromCallable() instead of Flowable.just().

//-BackpressureSupport - The operator fully supports backpressure and may coordinate downstream requests with upstream requests through batching,
// arbitration or by other means.
//-fromArray does not operate by default on a particular Scheduler.
public class Flowable_fromCallable {

	public Flowable_fromCallable() throws InterruptedException {
		
		List<Integer> list = Observable.range(0, 300).toList().blockingGet();
		
		Flowable.fromCallable(() -> list)
			.flatMap(e -> Flowable.fromArray(e.toArray()))
			.doOnNext(i -> System.out.println("Emitting " + i))
			.observeOn(Schedulers.io())
			.subscribe(i -> {
				Thread.sleep(500);
				System.out.println("Received " + i);
			});
				
		Thread.sleep(3000);
		
		
		
		
		// fromCallable()
		Flowable.fromCallable(() -> 1 / 0)
	    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("Callable Error Captured: " + e));
		
		// just()
		Flowable.just(1 / 0)
	    	.subscribe(i -> System.out.println("RECEIVED: " + i), e -> System.out.println("Just Error Captured: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_fromCallable();

	}

}
