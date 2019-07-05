package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that this TestObserver/TestSubscriber has not received any onError event.
public class TestObserver_assertNoErrors {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    
	    testObserver.assertNoErrors();
	
	}

}
