package rx.flowable.create;

import io.reactivex.Flowable;

//-It never calls onComplete(), forever leaving observers waiting for emissions but never actually giving any.
//-This Flowable is primarily used for testing and not that often in production. 

//-Returns a Flowable that never sends any items or notifications to a Subscriber. 
//-BackpressureSupport - The backpressure-related requests pass through this operator without change.
//-defer does not operate by default on a particular Scheduler.
public class Flowable_never {

	public Flowable_never() throws InterruptedException {
		
		Flowable<String> empty = Flowable.never();
			empty.subscribe(System.out::println, Throwable::getMessage, () -> System.out.println("Done!"));
		
		Thread.sleep(1000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Flowable_never();

	}

}
