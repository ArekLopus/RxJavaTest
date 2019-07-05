package rx.observable.backpr.buffering;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-You can use buffer() at fixed time intervals by providing a long and TimeUnit.
//-Code buffers emissions into a list at 1-second intervals. Note that we are making the source emit every 300 milliseconds,
// and each resulting buffered list will likely contain three or four emissions due to the one-second interval cut-offs.
//-Note that time-based buffer() operators will operate on the computation Scheduler.
// This makes sense since a separate thread needs to run on a timer to execute the cutoffs.
public class Buffer_time_ {

	public Buffer_time_() throws InterruptedException {
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
	    	.map(i -> (i + 1) * 300) 			// map to elapsed time
	    	.buffer(1, TimeUnit.SECONDS)
	    	.subscribe(System.out::println);
		
		Thread.sleep(4000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Buffer_time_();

	}

}



