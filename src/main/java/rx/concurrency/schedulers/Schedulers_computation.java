package rx.concurrency.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	Schedulers.computation()
//-This will maintain a fixed number of threads based on the processor count available to your Java session, making it appropriate
// for computational tasks (such as math, algorithms, and complex logic), they  may utilize cores to their fullest extent.
// Therefore, there is no benefit in having more worker threads than available cores to perform such work,
// and the computational Scheduler will ensure that.
//-When you are unsure how many tasks will be executed concurrently or are simply unsure which Scheduler is the right one to use,
// prefer the computation one by default. 
//-A number of operators and factories will use the computation Scheduler by default unless you specify a different one as an argument.
// These include interval(), delay(), timer(), timeout(), buffer(), take(), skip(), takeWhile(), skipWhile(), window(), and a few others.
public class Schedulers_computation {

	public Schedulers_computation() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.subscribeOn(Schedulers.computation())
			.subscribe(RxUtils::consumeStringWithThreadPrint);
		
		Thread.sleep(200);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Schedulers_computation();

	}

}



