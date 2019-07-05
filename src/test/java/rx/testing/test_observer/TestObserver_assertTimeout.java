package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Asserts that some awaitX method has timed out. 
public class TestObserver_assertTimeout {

	@Test
	public void test() throws InterruptedException {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.await(1, TimeUnit.SECONDS);
	    
	    
	    testObserver.assertTimeout();
	
	}

}
