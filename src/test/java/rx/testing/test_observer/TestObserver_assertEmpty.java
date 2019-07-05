package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the TestObserver/TestSubscriber has received a Disposable but no other events.
public class TestObserver_assertEmpty {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.dispose();
	    
	    
	    testObserver.assertEmpty();
	
	}

}
