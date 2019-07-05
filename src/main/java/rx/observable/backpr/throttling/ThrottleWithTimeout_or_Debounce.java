package rx.observable.backpr.throttling;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-While emissions are firing rapidly, it will not emit anything until there is a "period of silence", then it pushes the last emission forward.
///-throttleWithTimout() (also called debounce()) accepts time interval arguments that specify how long a period of inactivity
// (which means no emissions are coming from the source) must be before the last emission can be pushed forward.
//-The throttleWithTimeout() is an effective way to handle excessive inputs (such as a user clicking on a button rapidly)
// and other noisy, redundant events that sporadically speed up, slow down, or cease.
//-The only disadvantage of throttleWithTimeout() is that it will delay each winning emission. If an emission does make it through
// throttleWithTimeout(), it will be delayed by the specified time interval in order to ensure no more emissions are coming.
// Especially for user experiences, this artificial delay may be unwelcome.
//-For these situations, which are sensitive to delays, a better option might be to leverage switchMap().
//-Note that throttle() operators will operate on the computation Scheduler.

//-The 900 emission from source2 was the last emission as soon as source3 started, since source3 will not push its first emission for 2 seconds,
// which gave more than the needed 1-second period of silence for the 900 emission to be fired. 
public class ThrottleWithTimeout_or_Debounce {

	public ThrottleWithTimeout_or_Debounce() throws InterruptedException {
		
		Observable<String> source1 = Observable.interval(100, TimeUnit.MILLISECONDS)
			.map(i -> (i + 1) * 100)
			.map(i -> "Source 1: " + i)
			.take(10);
		Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
			.map(i -> (i + 1) * 300)
			.map(i -> "Source 2: " + i)
			.take(3);
		Observable<String> source3 = Observable.interval(2000, TimeUnit.MILLISECONDS)
			.map(i -> (i + 1) * 2000)
			.map(i -> "Source 3: " + i)
			.take(2);
		
		Observable.concat(source1, source2, source3)
	    	.throttleWithTimeout(1, TimeUnit.SECONDS)
	    	//.debounce(1, TimeUnit.SECONDS)
	    	.subscribe(System.out::println);
		
		Thread.sleep(6000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new ThrottleWithTimeout_or_Debounce();

	}

}