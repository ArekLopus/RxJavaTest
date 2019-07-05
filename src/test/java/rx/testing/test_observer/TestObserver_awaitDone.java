package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Awaits until the internal latch is counted down. 
//-If the wait times out or gets interrupted, the TestObserver/TestSubscriber is cancelled.
public class TestObserver_awaitDone {

	public TestObserver_awaitDone() throws InterruptedException {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		
		testObserver.awaitDone(1, TimeUnit.SECONDS);
		
		System.out.println(testObserver.values());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) throws Exception {
		new TestObserver_awaitDone();

	}
}
