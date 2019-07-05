package rx.operators.replay;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Just note that this can get expensive with memory, as replay() will keep caching all emissions it receives.
//-If the source is infinite or you only care about the last previous emissions, you might want to specify
// a bufferSize argument to limit only replaying a certain number of last emissions.
public class Replay_bufferSize {

	public Replay_bufferSize() throws InterruptedException {
		
		Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
		    .replay(1)
		    .autoConnect();
		
		seconds.subscribe(l -> System.out.println("Observer 1: " + l));		//Observer 1
		Thread.sleep(4000);
		
		// After 4 seconds, Observer 2 comes in and  receives only one emission it missed.
		seconds.subscribe(l -> System.out.println("Observer 2: " + l));		//Observer 2
		Thread.sleep(3000);
			
			
		System.out.println("--- Main Thread Finished ---");
			
	}

	public static void main(String[] args) throws Exception {
		new Replay_bufferSize();

	}

}
