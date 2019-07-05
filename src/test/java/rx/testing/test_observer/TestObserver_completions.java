package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Returns the number of times onComplete was called.
public class TestObserver_completions {

	public TestObserver_completions() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		System.out.println(testObserver.completions());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_completions();

	}
}
