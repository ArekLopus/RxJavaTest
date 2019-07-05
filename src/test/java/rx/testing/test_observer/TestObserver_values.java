package rx.testing.test_observer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Returns a shared list of received onNext values. 
public class TestObserver_values {

	public TestObserver_values() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		List<Long> values = testObserver.values();
		System.out.println(values);
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_values();

	}
}
