package rx.observable.disposable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Disposable_3 {

	public Disposable_3() throws InterruptedException {
		
		Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
		
		Disposable disposable = source.subscribe(e -> System.out.println("Received 1: " + e + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(3000);
		
		disposable.dispose();
		System.out.println("After disposable.dispose(), we can start another Observer.");
		source.subscribe(e -> System.out.println("Received 2: " + e + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Disposable_3();

	}

}
