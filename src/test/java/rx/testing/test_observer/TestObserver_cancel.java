package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Cancels the TestObserver (before or after the subscription happened).
public class TestObserver_cancel {

	public TestObserver_cancel() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		testObserver.cancel();
		
		System.out.println(testObserver.isCancelled());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_cancel();

	}
}
