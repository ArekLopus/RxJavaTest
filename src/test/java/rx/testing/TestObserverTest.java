package rx.testing;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-An Observer that records events and allows making assertions about them. 
//-You can override the onSubscribe, onNext, onError, onComplete, onSuccess andcancel methods but not the others (this is by design). 
//-The TestObserver implements Disposable for convenience where dispose calls cancel.

//-Prefer TestObserver and TestSubscriber over blocking operators as much as possible. 
//-TestObserver is used for Observable, Single, Maybe, and Completable sources.
//-Here is a unit test showcasing several TestObserver methods, which also exist on TestSubscriber if you are working with Flowables. 
//-These methods perform tasks such as asserting that certain events have (or have not) occurred, awaiting terminations
// or asserting that certain values were received.
//-Most of the TestObserver methods return TestObserver so you can actually chain these assertions fluently (also TestSubscriber).
//-TestObserver implements Observer, MaybeObserver, SingleObserver, and CompetableObserver to support all these reactive types.
//-If you live test a long-running asynchronous source, you might want to use awaitCount() to wait for a minimum number
// of emissions to assert with and not wait for the onComplete() call.

// assertComplete		assertEmpty				assertError			assertErrorMessage		assertFailure			assertFailureAndMessage
// assertNever			assertNoErrors			assertNotComplete	assertNoTimeout			assertNotSubscribed		assertNotTerminated
// assertNoValues		assertOf				assertResult		assertSubscribed		assertTerminated		assertTimeout		
// assertValue			assertValueAt			assertValueCount	assertValues			assertValueSequence		assertValueSequenceOnly
// assertValueSet		assertValueSetOnly		assertValuesOnly
public class TestObserverTest {

	@Test
	public void test() {
	    
	    Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5);
	    
	    TestObserver<Long> testObserver = new TestObserver<>();	//Declare TestObserver
	    
	    testObserver.assertNotSubscribed();				//Assert no subscription has occurred yet
	    
	    source.subscribe(testObserver);	    			//Subscribe TestObserver to source
	    testObserver.assertSubscribed();				//Assert TestObserver is subscribed
	    
	    testObserver.awaitTerminalEvent();				//Block and wait for Observable to terminate
	    testObserver.assertComplete();					//Assert TestObserver called onComplete()
	    testObserver.assertNoErrors();					//Assert there were no errors
	    
	    testObserver.assertValueCount(5);				//Assert 5 values were received
	    testObserver.assertValues(0L, 1L, 2L, 3L, 4L);	//Assert the received emissions were 0, 1, 2, 3, 4
	
	}

}
