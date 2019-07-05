package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that this TestObserver/TestSubscriber has not received any onNext events.
public class TestObserver_assertNoValues {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.empty();
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    testObserver.assertNoValues();
	
	}

}
