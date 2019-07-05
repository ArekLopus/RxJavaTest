package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Asserts that this TestObserver/TestSubscriber received an onNext value at the given index
// which is equal to the given value with respect to null-safe Object.equals. 
public class TestObserver_assertValueAt {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    
	    testObserver.assertValueAt(1, 1L);
	    
	    testObserver.assertValueAt(1, e -> e < 5);
	}

}
