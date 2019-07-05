package rx.operators_test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Creates a TestObserver and subscribes it to this Observable. 
public class TestOperator {

	@Test
	public void test() {
	    
	    TestObserver<Long> testObserver = Observable.interval(1, TimeUnit.MILLISECONDS)
	        .take(5)
	        .test();
	    
	    //TestObserver<Long> testObserver = new TestObserver<>();	//Declare TestObserver
	    //testObserver.assertNotSubscribed();				//Assert no subscription has occurred yet
	    //source.subscribe(testObserver);	    			//Subscribe TestObserver to source
	    
	    testObserver.assertSubscribed();				//Assert TestObserver is subscribed
	    
	    testObserver.awaitTerminalEvent();				//Block and wait for Observable to terminate
	    testObserver.assertComplete();					//Assert TestObserver called onComplete()
	    testObserver.assertNoErrors();					//Assert there were no errors
	    
	    testObserver.assertValueCount(5);				//Assert 5 values were received
	    testObserver.assertValues(0L, 1L, 2L, 3L, 4L);	//Assert the received emissions were 0, 1, 2, 3, 4
	
	}

}
