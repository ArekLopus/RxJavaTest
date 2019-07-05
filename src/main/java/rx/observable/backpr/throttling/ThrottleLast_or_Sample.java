package rx.observable.backpr.throttling;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-The throttleLast() operator (which is aliased as sample()) will only emit the last item at a fixed time interval.
//-This effectively samples emissions by dipping into the stream on a timer and pulling out the latest one.
//-If you want to throttle more liberally at larger time intervals, you will get fewer emissions as this effectively reduces the sample frequency.
//-If you want to throttle more aggressively at shorter time intervals, you will get more emissions, as this increases the sample frequency.
//-Both throttleFirst() and throttleLast() emit on the computation Scheduler, but you can specify your own Scheduler as a third argument.
//-Note that throttle() operators will operate on the computation Scheduler.
public class ThrottleLast_or_Sample {

	public ThrottleLast_or_Sample() throws InterruptedException {
		
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
			//.throttleLast(500, TimeUnit.MILLISECONDS)
			.throttleLast(1, TimeUnit.SECONDS)
			//.throttleLast(2, TimeUnit.SECONDS)
			//.sample(1, TimeUnit.SECONDS)
			.subscribe(System.out::println);
		
		Thread.sleep(4000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new ThrottleLast_or_Sample();

	}

}