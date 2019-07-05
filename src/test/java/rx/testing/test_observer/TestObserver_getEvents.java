package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Returns a list of 3 other lists: the first inner list contains the plain values received; the second list contains
// the potential errorsand the final list contains the potential completions as Notifications.
public class TestObserver_getEvents {

	public TestObserver_getEvents() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		System.out.println(testObserver.getEvents());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_getEvents();

	}
}
