package rx.observable.disposable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Disposable_2 {

	public Disposable_2() throws InterruptedException {
		
		Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
		
		Disposable disposable = source.subscribe(e -> System.out.println("Received 1: " + e + ", thread: " + Thread.currentThread().getName()));
		source.subscribe(e -> System.out.println("Received 2: " + e + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(3000);
		
		disposable.dispose();		//dispose and stop emissions
		System.out.println("After disposable.dispose(), second Observer is still working.");
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Disposable_2();

	}

}
