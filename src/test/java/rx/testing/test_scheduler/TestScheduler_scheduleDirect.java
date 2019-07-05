package rx.testing.test_scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

//-Schedules the given task on this Scheduler without any time delay. 
//-This method is safe to be called from multiple threads but there are no ordering or non-overlapping guarantees between tasks.

public class TestScheduler_scheduleDirect {

	@Test
	public void test() {
	    
		TestScheduler testScheduler = new TestScheduler();
		TestObserver<Long> testObserver = new TestObserver<>();
		
		Observable<Long> minuteTicker = Observable
			.interval(1, TimeUnit.MINUTES, testScheduler);
		
	    minuteTicker.subscribe(testObserver);
	    
	    testScheduler.scheduleDirect( () -> System.out.println("Starting") );
	    
	    testScheduler.advanceTimeTo(30, TimeUnit.SECONDS);
	    testObserver.assertValueCount(0);
	    
	    testScheduler.scheduleDirect( () -> System.out.println("After 30 secs") );
	    
	    testScheduler.advanceTimeTo(1, TimeUnit.MINUTES);
	    testObserver.assertValueCount(1);
	   
	    testScheduler.advanceTimeTo(60, TimeUnit.MINUTES);
	    testObserver.assertValueCount(60);
	    
	    
	}

}
