package rx.concurrency.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	Schedulers.io()
//-IO tasks such as reading and writing databases, web requests, and disk storage are less expensive on the CPU and often have idle time waiting
// for the data to be sent or come back.
//-This means you can create threads more liberally, and Schedulers.io() is appropriate for this.
//-It will maintain as many threads as there are tasks and will dynamically grow, cache, and reduce the number of threads as needed.
public class Schedulers_io {

	public Schedulers_io() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.subscribeOn(Schedulers.io())
			.subscribe(RxUtils::consumeStringWithThreadPrint);
		
		Thread.sleep(200);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Schedulers_io();

	}

}



