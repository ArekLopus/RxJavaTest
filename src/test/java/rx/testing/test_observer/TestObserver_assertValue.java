package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that this TestObserver/TestSubscriber received EXACTLY ONE onNext value which is equal tothe given value with respect to Objects.equals.
public class TestObserver_assertValue {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.just(1L);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    
	    testObserver.assertValue(1L);
	    
	    testObserver.assertValue(e -> e < 5);
	
	}

}
