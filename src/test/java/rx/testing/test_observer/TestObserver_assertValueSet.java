package rx.testing.test_observer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the TestObserver/TestSubscriber received only items that are in the specifiedcollection as well,
// irrespective of the order they were received. 
//-This helps asserting when the order of the values is not guaranteed, i.e., when merging asynchronous streams. 
//-To ensure that only the expected items have been received, no more and no less, in any order,
// apply assertValueCount(int) with expected.size().
public class TestObserver_assertValueSet {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    testObserver.awaitTerminalEvent();
	    
	    
	    testObserver.assertValueSet(Arrays.asList(0L,1L,2L,3L,4L));
	}

}
