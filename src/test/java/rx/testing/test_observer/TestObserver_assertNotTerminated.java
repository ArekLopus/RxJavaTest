package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the TestObserver/TestSubscriber has not terminated (i.e., the terminal latch is still non-zero).
public class TestObserver_assertNotTerminated {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    
	    
	    testObserver.assertNotTerminated();
	
	}

}
