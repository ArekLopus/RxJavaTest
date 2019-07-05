package rx.concurrency.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	Schedulers.newThread()
//-The Schedulers.newThread() factory will return a Scheduler that does not pool threads at all.
//-It will create a new thread for each Observer and then destroy the thread when it is done.
//-This is different than Schedulers.io() because it does not attempt to persist and cache threads for reuse.
//-This may be helpful in cases where you want to create, use, and then destroy a thread immediately so it does not take up memory.
//-But for complex applications generally, you will want to use Schedulers.io() so there is some attempt to reuse threads if possible.
//-You also have to be careful as Schedulers.newThread() can run amok in complex applications (as can Schedulers.io())
// and create a high volume of threads, which could crash your application.
public class Schedulers_newThread {

	public Schedulers_newThread() throws InterruptedException {
		
		Observable.range(1, 5)
    		.observeOn(Schedulers.single())
    		.map(e -> RxUtils.hardWork(e * 100))
    		.subscribe(RxUtils::consumeNumberWithThreadPrint);
	
		Observable.range(1, 5)
			.observeOn(Schedulers.single())
			.map(e -> RxUtils.hardWork(e * 100))
			.subscribe(RxUtils::consumeNumberWithThreadPrint);
		
		Thread.sleep(2200);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Schedulers_newThread();

	}

}



