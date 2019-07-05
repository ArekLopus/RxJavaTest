package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Having multiple processes occurring at the same time is what makes an application concurrent. 
//-It can result in much greater efficiency as it will utilize more cores and finish work more quickly.
//-subscribeOn() operator suggests to the source to fire emissions on a specified Scheduler.
//-If subscribeOn() is the only concurrent operation in an Observable chain, the thread from that Scheduler
// will work the entire Observable chain, pushing emissions from the source all the way to the final Observer. 
//-In this case, we use Schedulers.computation(), which pools a fixed number of threads appropriate for computation operations.
public class Concurrent_ {

	public Concurrent_() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.map(s -> RxUtils.hardWork(s.length() * 100) + " Observable 1, thread " + RxUtils.getThreadNr(23))
	    	.subscribeOn(Schedulers.computation())
	    	.subscribe(System.out::println);
		System.out.println();
		
		Observable.range(1,6)
	    	.map(s -> RxUtils.hardWork(s * 100) + " Observable 2, thread " + RxUtils.getThreadNr(23))
	    	.subscribeOn(Schedulers.computation())
	    	.subscribe(System.out::println);
		
		
		Thread.sleep(3000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception {
		new Concurrent_();

	}

}



