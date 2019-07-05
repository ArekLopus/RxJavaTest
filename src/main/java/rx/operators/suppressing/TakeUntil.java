package rx.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that emits the items emitted by the source Observable until a second ObservableSourc emits an item. 
//-takeUntil does not operate by default on a particular Scheduler.
public class TakeUntil {

	public TakeUntil() throws InterruptedException {
		
		System.out.println("Takes until timer emits.");
		Observable<Long> timer = Observable.timer(3, TimeUnit.SECONDS);
		timer.subscribe(s -> System.out.println("Timer emited, thread" + Thread.currentThread().getName()));
		
		Observable.interval(1, TimeUnit.SECONDS)
			.takeUntil(timer)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new TakeUntil();

	}

}
