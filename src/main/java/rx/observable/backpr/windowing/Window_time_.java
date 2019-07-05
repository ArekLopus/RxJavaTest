package rx.observable.backpr.windowing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-You can use window() at fixed time intervals by providing a long and TimeUnit.
//-Code windows emissions into a list at 1-second intervals. Note that we are making the source emit every 300 milliseconds,
// and each resulting window will likely contain three or four emissions due to the one-second interval cut-offs.
//-Note that time-based window() operators will operate on the computation Scheduler.
// This makes sense since a separate thread needs to run on a timer to execute the cutoffs.
public class Window_time_ {

	public Window_time_() throws InterruptedException {
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
			.window(1, TimeUnit.SECONDS)
			.flatMapSingle(obs -> obs.reduce("", (total, next) -> total + (total.equals("") ? "" : "-") + next))
	    	.subscribe(System.out::println);
		
		Thread.sleep(4000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Window_time_();

	}

}



