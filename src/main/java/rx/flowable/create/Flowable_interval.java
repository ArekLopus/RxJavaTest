package rx.flowable.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Returns an Observable that emits a sequential number every specified interval of time. 
//-Observable.interval() will emit infinitely at the specified interval 
//-These two observers are getting their own emissions, each starting at 0. So this Observable is actually cold.
//-Interval operates by default on the computation Scheduler. 

//-However, consider Flowable.interval(), which pushes time-based emissions at fixed time intervals.
// Can this be backpressured logically? Contemplate the fact that each emission is sensitively tied to the time it emits.
// If we slowed down Flowable.interval(), our emissions would no longer reflect time intervals and become misleading.
//-Therefore, Flowable.interval() is one of those few cases in the standard API that can throw MissingBackpressureException
// the moment downstream requests backpressure.
//-To overcome this issue, you can use operators such as onBackpresureDrop() or onBackPressureBuffer().

//-Flowable.interval() is one of those factories that logically cannot be backpressured at the source, so you can use operators after
// it to handle backpressure for you. Otherwise, most of the other Flowable factories you work with support backpressure.
public class Flowable_interval {

	public Flowable_interval() throws InterruptedException {
		
		Flowable.interval(1, TimeUnit.MILLISECONDS)
	    	.observeOn(Schedulers.io())
	    	.map(i -> RxUtils.hardWork(50))
	    	.subscribe(System.out::println, Throwable::printStackTrace); 
		
		Thread.sleep(3000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Flowable_interval();

	}

}
