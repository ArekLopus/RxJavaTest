package rx.observable.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Signals a range of long values, the first after some initial delay and the rest periodically after. 
//-The sequence completes immediately after the last value (start + count - 1) has been reached. 
//-intervalRange by default operates on the computation Scheduler.

//-start that start value of the range
//-count the number of values to emit in total, if zero, the operator emits an onComplete after the initial delay.
//-initialDelay the initial delay before signalling the first value (the start)
//-period the period between subsequent values
//-unit the unit of measure of the initialDelay and period amounts
public class Observable_intervalRange {

	public Observable_intervalRange() throws InterruptedException {
		
		long start = System.currentTimeMillis();
		
		Observable<Long> range = Observable.intervalRange(1, 10, 0, 2, TimeUnit.SECONDS);
		
		range.subscribe(e -> System.out.println("Observer 1: " + e  + ", after (ms): " + (System.currentTimeMillis() - start)));

		
		Thread.sleep(11000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_intervalRange();

	}

}
