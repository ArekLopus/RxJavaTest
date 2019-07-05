package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the onSubscribe method hasn't been called at all.
public class TestObserver_assertNotSubscribed {

	@Test
	public void test() {
	    
	    Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    
	    
	    testObserver.assertNotSubscribed();
	
	}

}
