package rx.testing.test_observer;

import java.util.Arrays;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Assert that the TestObserver/TestSubscriber received only the specified values in any order without terminating.
public class TestObserver_assertValueSetOnly {

	@Test
	public void test() {
	    
		Observable<Long> source = Observable.create(em-> {
			for(long l=0; l<5; l++) {
				em.onNext(l);
			}
		});
	    
	    TestObserver<Long> testObserver = new TestObserver<>();
	    source.subscribe(testObserver);
	    
	    
	    testObserver.assertValueSetOnly(Arrays.asList(0L,1L,2L,3L,4L));
	    
	}

}
