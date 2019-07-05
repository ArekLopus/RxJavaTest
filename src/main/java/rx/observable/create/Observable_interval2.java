package rx.observable.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

//-To put all observers on the same timer with the same emissions,  use ConnectableObservable to force these emissions to become hot.
public class Observable_interval2 {

	public Observable_interval2() throws InterruptedException {
		
		ConnectableObservable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish();
		
		seconds.subscribe(e -> System.out.println("Observer 1: " + e + ", thread: " + Thread.currentThread().getName()));	//observer 1
		seconds.connect();
		
		Thread.sleep(5000);
		seconds.subscribe(e -> System.out.println("Observer 2: " + e + ", thread: " + Thread.currentThread().getName()));	//observer 2
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Observable_interval2();

	}

}
