package rx.testing.test_observer;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Returns the number of onError exceptions received.
public class TestObserver_errorCount {

	public TestObserver_errorCount() {
	    
		Observable<Long> source = Observable.create(em -> {
			try {
				em.onNext(1L/0);
			} catch (Exception e) {
				em.onError(new ArithmeticException("by zero"));
			}
		});

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		testObserver.awaitTerminalEvent();
		
		System.out.println("Errors: " + testObserver.errorCount());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_errorCount();

	}
}
