package rx.concurrency.schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	Schedulers.from()
//-You can build a Scheduler off a standard Java ExecutorService. 
//-You may choose to do this in order to have more custom and fine-tuned control over your thread management policies.
//-Fe, say, we want to create a Scheduler that uses 20 threads. We can create a new fixed ExecutorService specified with this number of threads.
// Then, you can wrap it inside a Scheduler implementation by calling Schedulers.from().
//-ExecutorService will likely keep your program alive indefinitely, so you have to manage its disposal if its life is supposed to be finite.
// That is why shutdown() is called after the operation terminates or disposes via the doFinally() operator.
public class Schedulers_from_ES {
	
	private ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	public Schedulers_from_ES() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.subscribeOn(Schedulers.from(es))
	    	.doFinally(es::shutdown)
			.subscribe(RxUtils::consumeStringWithThreadPrint);
		Thread.sleep(200);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Schedulers_from_ES();

	}

}



