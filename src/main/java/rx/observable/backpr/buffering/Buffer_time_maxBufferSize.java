package rx.observable.backpr.buffering;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-You can also leverage a third count argument to provide a maximum buffer size.
//-This will result in a buffer emission at each time interval or when count is reached, whichever happens first.
//-If the count is reached right before the time window closes, it will result in an empty buffer being emitted.
//-Note that time-based buffer() operators will operate on the computation Scheduler.
public class Buffer_time_maxBufferSize {

	public Buffer_time_maxBufferSize() throws InterruptedException {
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
	    	.map(i -> (i + 1) * 300)
	    	.buffer(1, TimeUnit.SECONDS, 2)
	    	.subscribe(System.out::println);
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Buffer_time_maxBufferSize();

	}

}



