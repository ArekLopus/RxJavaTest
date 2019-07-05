package rx.flowable.create;

import io.reactivex.Flowable;

//-Returns a Flowable that emits no items to the Subscriber and immediately invokes its onComplete method. 
//-Empty flowables are common to represent empty datasets.
//-They can result from operators such as filter() when all emissions fail to meet a condition.
//-Sometimes, you will deliberately create empty Observables using Flowable.empty()
//-An empty Flowable is essentially RxJava's concept of null. It is the absence of a value.
//-Empty Flowables are much more elegant than nulls because operations will simply continue empty rather than throw NullPointerExceptions.

//-BackpressureSupport - The backpressure-related requests pass through this operator without change.
public class Flowable_empty {

	public Flowable_empty() throws InterruptedException {
		
		Flowable<Object> empty = Flowable.empty();
			empty.subscribe(System.out::println, Throwable::getMessage, () -> System.out.println("Done!"));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Flowable_empty();

	}

}
