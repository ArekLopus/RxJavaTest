package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that there is a single error and it has the given message.
public class TestObserver_assertErrorMessage {

	@Test
	public void test() {
	    
		Observable<Long> source = Observable.create(em -> {
			try {
				em.onNext(1L/0);
			} catch (Exception e) {
				em.onError(new ArithmeticException("by zero"));
			}
		});

	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    
	    
	    testObserver.assertErrorMessage("by zero");

	}

}
