package rx.testing.test_observer;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Returns a shared list of received onError exceptions. 
public class TestObserver_errors {

	public TestObserver_errors() {
	    
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
		
		System.out.println("Errors: " + testObserver.errors());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new TestObserver_errors();

	}
}
