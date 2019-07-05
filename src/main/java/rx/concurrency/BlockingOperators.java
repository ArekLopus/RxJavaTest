package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	More: operators.blocking
//-You can use blocking operators to stop the declaring thread and wait for emissions.
//-Usually, blocking operators are used for unit testing, and they can attract antipatterns if used improperly in production.
//-However, keeping an application alive based on the life cycle of a finite Observable subscription is a valid case to use a blocking operator.
//-blockingSubscribe() can be used in place of subscribe() to stop and wait for onComplete() to be called
// before the main thread is allowed to proceed and exit the application:
public class BlockingOperators {

	public BlockingOperators() {
		
		Observable.just("One", "Two", "Three")
	    	.map(s -> RxUtils.hardWork(s.length() * 100))
	    	.subscribeOn(Schedulers.computation())
	    	.blockingSubscribe(System.out::println);
		System.out.println();
		
		Observable.range(1,6)
	    	.map(s -> RxUtils.hardWork(s * 100) + ", thread " + Thread.currentThread().getName())
	    	.subscribeOn(Schedulers.computation())
	    	.blockingSubscribe(System.out::println);
		
		//  No Thread.sleep()
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) {
		new BlockingOperators();

	}

}



