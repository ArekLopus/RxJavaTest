package rx.operators.transforming;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Observable that emits the items emitted by the source ObservableSource shifted forward in time by a specified delay.
// Error notifications from the source ObservableSource are not delayed.
//-delay(long) operates by default on the computation Scheduler.
public class Delay {

	public Delay() throws InterruptedException {
		
		Observable<Integer> source = Observable.just(1, 2, 3);
		Observable<Integer> source1 = Observable.just(4, 5, 6);
		Observable<String> source2 = Observable.just("One", "Two", "Three");
		
		source
			.sorted()
			.delay(2, TimeUnit.SECONDS)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		source
			.delay(e -> source1)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		source
		.delay(source1, e -> source2)
			.subscribe(s -> System.out.println(s + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Delay();

	}

}
