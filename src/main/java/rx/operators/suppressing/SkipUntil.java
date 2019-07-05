package rx.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that skips items emitted by the source ObservableSource until a second ObservableSource emits an item. 
//-skipUntil does not operate by default on a particular Scheduler.
public class SkipUntil {

	public SkipUntil() throws InterruptedException {
		
		System.out.println("Waiting 3 secs until timer emits.");
		Observable<Long> timer = Observable.timer(3, TimeUnit.SECONDS);
		timer.subscribe(s -> System.out.println("Timer emited, thread" + Thread.currentThread().getName()));
		
		Observable.interval(1, TimeUnit.SECONDS)
			.skipUntil(timer)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new SkipUntil();

	}

}
