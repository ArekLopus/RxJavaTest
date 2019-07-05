package rx.flowable.create;

import io.reactivex.Flowable;

//-For testing, this Flowable immediately calls onError() with a specified exception: 
// 	error(Callable)	- Exception is created from scratch and new exception instances are provided to each Flowable
//	error(Throwable)

//-Returns a Flowable that invokes a Subscriber's onError method when theSubscriber subscribes to it. 
//-BackpressureSupport - The backpressure-related requests pass through this operator without change.
//-defer does not operate by default on a particular Scheduler.
public class Flowable_error {

	public Flowable_error() throws InterruptedException {
		
		Flowable.error(new Exception("Something is wrong!"))
			.subscribe(i -> System.out.println("Received: " + i), Throwable::printStackTrace, () -> System.out.println("Done!"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Flowable_error();

	}

}
