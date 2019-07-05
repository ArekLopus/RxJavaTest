package rx.concurrency;

import java.net.URL;
import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-Most Observable factories, such as Observable.fromIterable() or.just(), will emit items on the Scheduler specified by subscribeOn().
//-For factories such as Observable.fromCallable() and Observable.defer(), the initialization of these sources will also run on
// the specified Scheduler when using subscribeOn().
//-For instance, if you use Observable.fromCallable() to wait on a URL response, you can actually do that work on the IO Scheduler
// so the main thread is not blocking and waiting for it:
public class SubscribeOn_io {
	
	@SuppressWarnings("resource")
	public SubscribeOn_io() throws Exception {
		
		String path = "https://jsonplaceholder.typicode.com/todos/1";
		Scanner scanner = new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A");
		
		Observable.fromCallable(() -> scanner.next())
			.subscribeOn(Schedulers.io())
			.doFinally(scanner::close)
			.subscribe(e -> System.out.println("Thread: " + Thread.currentThread().getName() + "\n" + e));
		
		Thread.sleep(10000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new SubscribeOn_io();

	}

}



