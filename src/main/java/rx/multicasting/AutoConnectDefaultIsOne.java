package rx.multicasting;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-If you pass no argument for numberOfSubscribers, it will default to 1.
//-This can be helpful if you want it to start firing on the first subscription and do not care about any subsequent Observers
// missing previous emissions.
//-Here, we publish and autoConnect the Observable.interval(). The first Observer starts the firing of emissions, and 3 seconds later,
// another Observer comes in but misses the first few emissions. But it does receive the live emissions from that point on: 
public class AutoConnectDefaultIsOne {

	public AutoConnectDefaultIsOne() throws InterruptedException {
		
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
		    .publish()
		    .autoConnect();
		
		seconds.subscribe(i -> System.out.println("Observer 1: " + i));		//Observer 1
		Thread.sleep(3000);
		
		seconds.subscribe(i -> System.out.println("Observer 2: " + i));		//Observer 2
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new AutoConnectDefaultIsOne();

	}

}
