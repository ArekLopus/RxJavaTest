package rx.testing.test_scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

//-Moves the Scheduler's clock to a particular moment in time.
public class TestScheduler_advanceTimeTo {

	@Test
	public void test() {
	    
		TestScheduler testScheduler = new TestScheduler();
		TestObserver<Long> testObserver = new TestObserver<>();
		
		Observable<Long> minuteTicker = Observable
			.interval(1, TimeUnit.MINUTES, testScheduler);
		
	    minuteTicker.subscribe(testObserver);
	    
	    
	    testScheduler.advanceTimeTo(30, TimeUnit.SECONDS);
	    testObserver.assertValueCount(0);
	    
	    testScheduler.advanceTimeTo(1, TimeUnit.MINUTES);
	    testObserver.assertValueCount(1);
	   
	    testScheduler.advanceTimeTo(60, TimeUnit.MINUTES);
	    testObserver.assertValueCount(60);
	    
	}

}
