package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Asserts that this TestObserver/TestSubscriber received exactly one onError event which is an instance of the specified errorClass class.
public class TestObserver_assertError {

	@Test
	public void test() {
	    
		Observable<Long> source = Observable.create(em -> {
			em.onNext(1L/0);
		});

	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    
	    
	    testObserver.assertError(ArithmeticException.class);
		
	}

}
