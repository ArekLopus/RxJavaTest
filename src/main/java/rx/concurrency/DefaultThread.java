package rx.concurrency;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-By default, Observables execute work on the immediate thread, which is the thread that declared the Observer and subscribed it.
//-In many of examples, this was the main thread that kicked off our main() method.

//-But not all Observables will fire on the immediate thread.
//-Fe Observable.interval() fires on a thread other than the main one. The main thread kicks-off Observable.interval(),
// but not wait for it to complete because it is operating on its own separate thread now.

//-A number of operators and factories will use the computation Scheduler by default unless you specify a different one as an argument.
// These include interval(), delay(), timer(), timeout(), buffer(), take(), skip(), takeWhile(), skipWhile(), window(), and a few others.
public class DefaultThread {

	public DefaultThread() throws InterruptedException {
		
		Observable<Integer> source = Observable.just(1, 2, 3);
		
		source.subscribe(RxUtils::consumeNumberWithThreadPrint);
		
		Observable.interval(1, TimeUnit.SECONDS)
			.map(i -> (i + 1) + ", thread: " + Thread.currentThread().getName())
			.subscribe(System.out::println);
		
		Thread.sleep(4000);
		
		
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new DefaultThread();

	}

}



