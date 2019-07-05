package rx.testing.test_scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

//-Use TestScheduler when you need to virtually represent time elapsing, but note that it is not a thread-safe Scheduler
// and should not be used with actual concurrency.
//-If we have a lot of unit tests that deal with time-driven sources, it can take a long time for testing to complete.
//-The TestScheduler does exactly this. It is a Scheduler implementation that allows us to fast-forward by a specific amount of elapsed time,
// and we can do any assertions after each fast-forward to see what events have occurred.
//-Carefully note that advanceTimeBy() will fast-forward the specified time interval relative to the current time,
// whereas advanceTimeTo() will jump to the exact time elapsed since the subscription has occurred.
// -A common pitfall is complicated flows that use many operators and Schedulers are not easily configurable to use TestScheduler.
// In this case, you can use RxJavaPlugins.setComputationScheduler() and similar methods that override the standard Schedulers
// and inject TestScheduler in its place.
//-There are two other methods to note in TestScheduler.
//-now() will return how much time has virtually elapsed in the unit you specify.
//-triggerActions() method will kick-off any actions that are scheduled to be triggered, but have not virtually been elapsed yet.
public class TestScheduler_ {

	@Test
	public void test() {
	    
		TestScheduler testScheduler = new TestScheduler();			//Declare TestScheduler
		TestObserver<Long> testObserver = new TestObserver<>();		//Declare TestObserver
		
		Observable<Long> minuteTicker = Observable
			.interval(1, TimeUnit.MINUTES, testScheduler);
		
	    minuteTicker.subscribe(testObserver);						//Subscribe to TestObserver
	    
	    testScheduler.advanceTimeBy(30, TimeUnit.SECONDS);			//Fast forward by 30 seconds
	    testObserver.assertValueCount(0);							//Assert no emissions have occurred yet
	    
	    testScheduler.advanceTimeTo(70, TimeUnit.SECONDS);			//Fast forward to 70 seconds after subscription
	    testObserver.assertValueCount(1);							//Assert the first emission has occurred
	   
	    testScheduler.advanceTimeTo(90, TimeUnit.MINUTES);			//Fast Forward to 90 minutes after subscription
	    testObserver.assertValueCount(90);							//Assert 90 emissions have occurred
	    
	}

}
