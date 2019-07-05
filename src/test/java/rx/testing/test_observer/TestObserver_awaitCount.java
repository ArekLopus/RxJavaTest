package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Await until the TestObserver/TestSubscriber receives the given number of items or terminates by sleeping 10 milliseconds
// at a timeup to 5000 milliseconds of timeout. 
public class TestObserver_awaitCount {

	public TestObserver_awaitCount() throws InterruptedException {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		
		testObserver.awaitCount(5);
		
		System.out.println(testObserver.values());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) throws Exception {
		new TestObserver_awaitCount();

	}
}
