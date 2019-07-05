package rx.concurrency;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//-When you dispose an Observable, sometimes, that can be an expensive operation depending on the nature of the source.
//-This can cause the thread that calls dispose() to become busy, as it will be doing all the work stopping
// an Observable subscription and disposing it.
//-You can put unsubscribeOn() wherever you want all operations upstream to be affected.
// This allows the main thread to kick off disposal and continue without waiting for it to complete.
//-You can use multiple unsubscribeOn() calls if you want to target specific parts of the Observable chain to be disposed of with different Schedulers.
// Everything upstream to an unsubscribeOn() will be disposed of with that Scheduler
// until another unsubscribeOn() is encountered, which will own the next upstream segment.
//-You really should not need to use unsubscribeOn() for lightweight operations such as this example, as it adds unnecessary overhead.
//-But if you have Observable operations that are heavy with resources which are slow to dispose of,
// unsubscribeOn() can be a crucial tool if threads calling dispose() are sensitive to high workloads.
public class UnsubscribeOn_ {

	public UnsubscribeOn_() throws InterruptedException {
		
		Disposable d = Observable.interval(1, TimeUnit.SECONDS)
			.doOnDispose(() -> System.out.println("Disposed, thread: " + Thread.currentThread().getName()))
			.subscribe(i -> System.out.println("Val: " + i));
		
		Thread.sleep(3000);
		
		d.dispose();
		
		Thread.sleep(3000);
		
		Disposable d2 = Observable.interval(1, TimeUnit.SECONDS)
			.doOnDispose(() -> System.out.println("Disposed, thread: " + Thread.currentThread().getName()))
			.unsubscribeOn(Schedulers.io())
			.subscribe(i -> System.out.println("Val: " + i));
			
		Thread.sleep(3000);
			
		d2.dispose();
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new UnsubscribeOn_();

	}

}