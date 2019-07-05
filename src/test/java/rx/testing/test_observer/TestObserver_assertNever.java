package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Asserts that this TestObserver/TestSubscriber did not receive any onNext value for whichthe provided predicate returns true. 
public class TestObserver_assertNever {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);													// (0,1,2,3,4)
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    testObserver.assertNever(5L);
	    
	    testObserver.assertNever(e -> e > 5);
	}

}
