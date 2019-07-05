package rx.observable.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that emits a sequential number every specified interval of time. 
//-Observable.interval() will emit infinitely at the specified interval 
//-These two observers are getting their own emissions, each starting at 0. So this Observable is actually cold.
//-Interval operates by default on the computation Scheduler. 
public class Observable_interval {

	public Observable_interval() throws InterruptedException {
		
		Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
		
		source.subscribe(e-> System.out.println("Observer1: " + e));
		Thread.sleep(5000);
		
		source.subscribe(e-> System.out.println("Observer2: " + e));
		Thread.sleep(5000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_interval();

	}

}
