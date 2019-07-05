package rx.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that skips values emitted by the source ObservableSource before a specified time window elapses.  
//-Skip does not operate on any particular scheduler but uses the current time from the computation Scheduler.
public class Skip_time {

	public Skip_time() throws Exception {
		
		Observable.interval(1, TimeUnit.SECONDS)
				.skip(4, TimeUnit.SECONDS)
				.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Skip_time();

	}

}
