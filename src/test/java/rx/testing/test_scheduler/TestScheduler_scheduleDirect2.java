package rx.testing.test_scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

//-Schedules the execution of the given task with the given time delay. 
//-This method is safe to be called from multiple threads but there are no ordering or non-overlapping guarantees between tasks.

public class TestScheduler_scheduleDirect2 {

	@Test
	public void test() {
	    
		TestScheduler testScheduler = new TestScheduler();
		TestObserver<Long> testObserver = new TestObserver<>();
		
		Observable<Long> minuteTicker = Observable
			.interval(1, TimeUnit.MINUTES, testScheduler);
		
	    minuteTicker.subscribe(testObserver);
	    
	    testScheduler.scheduleDirect( () -> {
	    	System.out.println("After 2 minutes");
	    	System.out.println("Now (secs): " + testScheduler.now(TimeUnit.SECONDS));
	    }, 2, TimeUnit.MINUTES);
	    
	    testScheduler.advanceTimeTo(30, TimeUnit.SECONDS);
	    testObserver.assertValueCount(0);
	    
	    testScheduler.advanceTimeTo(1, TimeUnit.MINUTES);
	    testObserver.assertValueCount(1);
	   
	    testScheduler.advanceTimeTo(60, TimeUnit.MINUTES);
	    testObserver.assertValueCount(60);
	    
	    
	}

}
