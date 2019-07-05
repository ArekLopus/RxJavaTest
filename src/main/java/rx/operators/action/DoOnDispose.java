package rx.operators.action;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

//-Calls the dispose Action if the downstream disposes the sequence. 
//-The action is shared between subscriptions and thus may be called concurrently from multiplethreads; the action must be thread safe. 
//-If the action throws a runtime exception, that exception is rethrown by the dispose() call,
// sometimes as a CompositeException if there were multiple exceptions along the way. 
//-doOnDispose does not operate by default on a particular Scheduler.
public class DoOnDispose {
	
	public DoOnDispose() throws InterruptedException {
		
		Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
			.doOnDispose( () -> System.out.println("Disposed.") )
			.subscribe(s -> System.out.println("Received: " + s));
		
		Thread.sleep(3000);
		
		disp.dispose();
		
		Thread.sleep(2000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new DoOnDispose();

	}

}
