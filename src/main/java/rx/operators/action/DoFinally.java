package rx.operators.action;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

//-Calls the specified action after this Observable signals onError or onComplete or gets disposed by the downstream. 
//-In case of a race between a terminal event and a dispose call, the provided onFinally action is executed once per subscription. 
//-Note that the onFinally action is shared between subscriptions and as such should be thread-safe. 
//-doFinally does not operate by default on a particular Scheduler.
public class DoFinally {
	
	public DoFinally() throws InterruptedException {
		
		Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
				.doFinally( () -> System.out.println("doFinally Interval") )
				.subscribe(s -> System.out.println("Received: " + s));
		Thread.sleep(3000);
			
		disp.dispose();
		System.out.println();
		
		
		Observable.error(new IllegalStateException("Bad!"))
				.doFinally( () -> System.out.println("doFinally Just") )
				.subscribe(s -> System.out.println("Received: " + s), e -> System.out.println("ERROR: " + e.getMessage()));
		
		Thread.sleep(2000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new DoFinally();

	}

}
