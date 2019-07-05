package rx.testing.test_observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

//-Awaits the specified amount of time or until this TestObserver/TestSubscriberreceives an onError or onComplete events, whichever happens first.
public class TestObserver_await {

	public TestObserver_await() throws InterruptedException {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
			.take(5);

		TestObserver<Long> testObserver = new TestObserver<>();
		source.subscribe(testObserver);
		
		testObserver.await(1, TimeUnit.SECONDS);
		
		System.out.println(testObserver.values());
		
		
		System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) throws Exception {
		new TestObserver_await();

	}
}
