package rx.concurrency.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//	Schedulers.trampoline()
//-It is just like default scheduling on the immediate thread, but it prevents cases of recursive scheduling where a task schedules a task
// while on the same thread. Instead of causing a stack overflow error, it will allow the current task to finish
// and then execute that new scheduled task afterward.
//-You will not invoke it often as it is used primarily in RxJava's internal impl.
//-Its pattern is also borrowed for UI Schedulers such as RxJavaFX and RxAndroid.
public class Schedulers_trampoline {

	public Schedulers_trampoline() throws InterruptedException {
		
		Observable.just("One", "Two", "Three")
	    	.subscribeOn(Schedulers.trampoline())
			.subscribe(RxUtils::consumeStringWithThreadPrint);
		
		Thread.sleep(200);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Schedulers_trampoline();

	}

}



