package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Returns the last thread which called the onXXX methods of this TestObserver/TestSubscriber.
public class TestObserver_lastThread {

	public TestObserver_lastThread() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		System.out.println(testObserver.lastThread());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_lastThread();

	}
}
