package rx.observable.backpr.windowing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-There is an option to also specify a timeskip argument, which is the timer-based counterpart to skip.
// It controls the timing of when each window starts.
//-Note that time-based window() operators will operate on the computation Scheduler.
public class Window_time_skipTime {

	public Window_time_skipTime() throws InterruptedException {
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
	    	.window(1, 2, TimeUnit.SECONDS)						// skip every 2 seconds (1 ,3)
	    	.flatMapSingle(obs -> obs.reduce("", (total, next) -> total + (total.equals("") ? "" : "-") + next))
	    	.subscribe(System.out::println);
		
		Thread.sleep(6000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Window_time_skipTime();

	}

}



