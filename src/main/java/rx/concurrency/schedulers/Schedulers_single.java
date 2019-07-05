package rx.concurrency.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	Schedulers.single()
//-When you want to run tasks sequentially on a single thread, you can invoke Schedulers.single().
//-This is backed by a single-threaded implementation appropriate for event looping.
// It can also be helpful to isolate fragile, non-threadsafe operations to a single thread.
public class Schedulers_single {

	public Schedulers_single() throws InterruptedException {
		
		Observable.range(1, 5)
	    	.subscribeOn(Schedulers.single())
			.subscribe(RxUtils::consumeNumberWithThreadPrint);
		
		Observable.range(1, 5)
    		.subscribeOn(Schedulers.single())
    		.subscribe(RxUtils::consumeNumberWithThreadPrint);
		
		Thread.sleep(200);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Schedulers_single();

	}

}



