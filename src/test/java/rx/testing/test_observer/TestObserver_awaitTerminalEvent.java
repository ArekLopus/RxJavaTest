package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Waits until the any terminal event has been received by this TestObserver/TestSubscriberor returns false if the wait has been interrupted.
public class TestObserver_awaitTerminalEvent {

	public TestObserver_awaitTerminalEvent() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		
		testObserver.awaitTerminalEvent();
		
		System.out.println(testObserver.values());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_awaitTerminalEvent();

	}
}
