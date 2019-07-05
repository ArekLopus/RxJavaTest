package rx.testing.test_scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

//-Schedules a periodic execution of the given task with the given initial time delay and repeat period. 
//-This method is safe to be called from multiple threads but there are noordering guarantees between tasks. 
//-The periodic execution is at a fixed rate, that is, the first execution will be after the initialDelay,
// the second after initialDelay + period, the third after initialDelay + 2 * period, and so on.
public class TestScheduler_schedulePeriodicallyDirect {

	@Test
	public void test() {
	    
		TestScheduler testScheduler = new TestScheduler();
		TestObserver<Long> testObserver = new TestObserver<>();
		
		Observable<Long> minuteTicker = Observable
			.interval(1, TimeUnit.MINUTES, testScheduler);
		
	    minuteTicker.subscribe(testObserver);
	    
	    testScheduler.schedulePeriodicallyDirect( () -> {
	    	System.out.println("schedulePeriodicallyDirect every 10 min, now (secs): " + testScheduler.now(TimeUnit.SECONDS));
	    }, 0, 10, TimeUnit.MINUTES);
	    
	    testScheduler.advanceTimeTo(30, TimeUnit.SECONDS);
	    testObserver.assertValueCount(0);
	    
	    testScheduler.advanceTimeTo(1, TimeUnit.MINUTES);
	    testObserver.assertValueCount(1);
	   
	    testScheduler.advanceTimeTo(60, TimeUnit.MINUTES);
	    testObserver.assertValueCount(60);
	    
	    
	}

}
