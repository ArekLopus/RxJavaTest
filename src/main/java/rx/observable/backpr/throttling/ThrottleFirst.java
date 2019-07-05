package rx.observable.backpr.throttling;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-The throttleFirst() operates almost identically to throttleLast(), but it will emit the first item that occurs at every fixed time interval.
//-Effectively, the first emission found after each interval starts is the emission that gets pushed through.
//-Besides the first item being emitted rather than the last at each interval, all the behaviors from throttleLast() also apply
// to throttleFirst(). Specifying shorter intervals will yield more emissions, whereas longer intervals will yield less.
//-Both throttleFirst() and throttleLast() emit on the computation Scheduler, but you can specify your own Scheduler as a third argument.
//-Note that throttle() operators will operate on the computation Scheduler.
public class ThrottleFirst {

	public ThrottleFirst() throws InterruptedException {
		
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
			//.throttleFirst(500, TimeUnit.MILLISECONDS)
			.throttleFirst(1, TimeUnit.SECONDS)
			//.throttleFirst(2, TimeUnit.SECONDS)
			.subscribe(System.out::println);
		
		Thread.sleep(4000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new ThrottleFirst();

	}

}