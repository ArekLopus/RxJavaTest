package rx.flowable.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;

//-To put all Flowables on the same timer with the same emissions,  use ConnectableFlowable to force these emissions to become hot.
public class ConnectableFlowableTest {

	public ConnectableFlowableTest() throws InterruptedException {
		
		ConnectableFlowable<Long> seconds = Flowable.interval(1, TimeUnit.SECONDS).publish();
		
		seconds.subscribe(e -> System.out.println("Observer 1: " + e + ", thread: " + Thread.currentThread().getName()));	// Flowable 1
		
		seconds.connect();
		Thread.sleep(5000);
		
		seconds.subscribe(e -> System.out.println("Observer 2: " + e + ", thread: " + Thread.currentThread().getName()));	// Flowable 2
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new ConnectableFlowableTest();

	}

}
