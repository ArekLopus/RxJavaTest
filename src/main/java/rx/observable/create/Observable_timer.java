package rx.observable.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that emits 0L after a specified delay, and then completes. 
public class Observable_timer {

	public Observable_timer() throws InterruptedException {
		
		long start = System.currentTimeMillis();
		
		System.out.println("Starting...");
		
		Observable<Long> source = Observable.timer(2, TimeUnit.SECONDS);
		
		source.subscribe(e-> System.out.println("Observer1: " + e + ", after (ms): " + (System.currentTimeMillis() - start)));
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_timer();

	}

}
