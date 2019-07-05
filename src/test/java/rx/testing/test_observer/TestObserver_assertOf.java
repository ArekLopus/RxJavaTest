package rx.testing.test_observer;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Run a check consumer with this TestObserver instance.
public class TestObserver_assertOf {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.empty();
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    testObserver.assertOf(e -> e.assertComplete());
	
	}

}
