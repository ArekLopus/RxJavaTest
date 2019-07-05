package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the upstream signalled the specified values in orderand then failed with a specific class or subclass of Throwable.
public class TestObserver_assertFailure {

	@Test
	public void test() {
	    
		Observable<Long> source = Observable.create(em -> {
			em.onNext(1L/0);
		});

	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    
	    
	    testObserver.assertFailure(ArithmeticException.class);
	    
	    testObserver.assertFailure(exc -> {
			return exc instanceof ArithmeticException;
		});
	}

}
