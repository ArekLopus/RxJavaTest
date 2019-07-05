package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the upstream signalled the specified values in order,then failed with a specific class or subclass of Throwable
// and with the given exact error message.
public class TestObserver_assertFailureAndMessage {

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
	    
	    
	    testObserver.assertFailureAndMessage(ArithmeticException.class, "by zero");
	    
	}

}
