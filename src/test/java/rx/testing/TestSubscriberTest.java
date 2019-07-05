package rx.testing;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

//-A subscriber that records events and allows making assertions about them. 
//-You can override the onSubscribe, onNext, onError, onComplete, request andcancel methods but not the others (this is by design). 
//-The TestSubscriber implements Disposable for convenience where dispose calls cancel. 
//-When calling the default request method, you are requesting on behalf of the wrapped actual subscriber.

// assertComplete		assertEmpty				assertError			assertErrorMessage		assertFailure			assertFailureAndMessage
// assertNever			assertNoErrors			assertNotComplete	assertNoTimeout			assertNotSubscribed		assertNotTerminated
// assertNoValues		assertOf				assertResult		assertSubscribed		assertTerminated		assertTimeout		
// assertValue			assertValueAt			assertValueCount	assertValues			assertValueSequence		assertValueSequenceOnly
// assertValueSet		assertValueSetOnly		assertValuesOnly
public class TestSubscriberTest {

	@Test
	public void test() {
	    
	    Flowable<Long> source = Flowable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestSubscriber<Long> testSubscriber = new TestSubscriber<>();	//Declare TestObserver
	    testSubscriber.assertNotSubscribed();				//Assert no subscription has occurred yet
	    
	    source.subscribe(testSubscriber);	    			//Subscribe TestObserver to source
	    testSubscriber.assertSubscribed();					//Assert TestObserver is subscribed
	    
	    testSubscriber.awaitTerminalEvent();				//Block and wait for Observable to terminate
	    testSubscriber.assertComplete();					//Assert TestObserver called onComplete()
	    testSubscriber.assertNoErrors();					//Assert there were no errors
	    
	    testSubscriber.assertValueCount(5);					//Assert 5 values were received
	    testSubscriber.assertValues(0L, 1L, 2L, 3L, 4L);	//Assert the received emissions were 0, 1, 2, 3, 4
	
	}

}
