package rx.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that emits those items emitted by source ObservableSource before a specified time runs out. 
//-If time runs out before the Observable completes normally, the onComplete event will besignaled on the default computation Scheduler. 
//-This version of take operates by default on the computation Scheduler.
public class Take_time {

	public Take_time() throws Exception {
		
		Observable.interval(1, TimeUnit.SECONDS)
				.take(4, TimeUnit.SECONDS)
				.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Take_time();

	}

}
