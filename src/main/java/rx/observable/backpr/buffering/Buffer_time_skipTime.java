package rx.observable.backpr.buffering;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-There is an option to also specify a timeskip argument, which is the timer-based counterpart to skip.
// It controls the timing of when each buffer starts.
//-Note that time-based buffer() operators will operate on the computation Scheduler.
public class Buffer_time_skipTime {

	public Buffer_time_skipTime() throws InterruptedException {
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
	    	.map(i -> (i + 1) * 300)
	    	.buffer(1, 2, TimeUnit.SECONDS)		// skip every 2 seconds (1 ,3)
	    	.subscribe(System.out::println);
		
		Thread.sleep(6000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Buffer_time_skipTime();

	}

}



