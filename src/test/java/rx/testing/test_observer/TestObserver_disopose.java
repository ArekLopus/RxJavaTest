package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Dispose the resource, the operation should be idempotent.
public class TestObserver_disopose {

	public TestObserver_disopose() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		testObserver.dispose();
		System.out.println(testObserver.isDisposed());
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_disopose();

	}
}
