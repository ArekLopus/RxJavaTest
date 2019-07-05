package rx.testing.test_scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

//-Returns the 'current time' of the Scheduler in the specified time unit.
public class TestScheduler_now {

	@Test
	public void test() {
	    
		TestScheduler testScheduler = new TestScheduler();
		TestObserver<Long> testObserver = new TestObserver<>();
		
		Observable<Long> minuteTicker = Observable
			.interval(1, TimeUnit.MINUTES, testScheduler);
		
	    minuteTicker.subscribe(testObserver);
	    
	    
	    testScheduler.advanceTimeTo(30, TimeUnit.SECONDS);
	    testObserver.assertValueCount(0);
	    
	    System.out.println(testScheduler.now(TimeUnit.SECONDS));
	    
	    testScheduler.advanceTimeTo(1, TimeUnit.MINUTES);
	    testObserver.assertValueCount(1);
	    
	    System.out.println(testScheduler.now(TimeUnit.SECONDS));
	    
	    testScheduler.advanceTimeTo(60, TimeUnit.MINUTES);
	    testObserver.assertValueCount(60);
	    
	    System.out.println(testScheduler.now(TimeUnit.SECONDS));
	}

}
