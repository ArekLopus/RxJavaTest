package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that this TestObserver/TestSubscriber has not received any onComplete event.
public class TestObserver_assertNotComplete {

	@Test
	public void test() {
	    
		Observable<Long> source = Observable.create(em -> {
			em.onNext(1L/0);
		});

	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    
	    
	    testObserver.assertNotComplete();
	    
	}

}
