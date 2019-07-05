package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-Use observeOn() to intercept each emission and push them forward on a different Scheduler (You can set subscribeOn() only once).
//-The observeOn() operator intercepts emissions at the point in the Observable chain and switch them to a different Scheduler going forward.
//-Unlike subscribeOn(), the placement of observeOn() matters.
// It will leave all operations upstream on the default or subscribeOn()-defined Scheduler, but will switch to a different Scheduler downstream.
public class ObserveOn_ {

	public ObserveOn_() throws InterruptedException {
		
		//Happens on IO Scheduler
		Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
		    .subscribeOn(Schedulers.io())
		    .flatMap(s -> Observable.fromArray(s.split("/")))
		    .doOnNext(e -> System.out.println("doOnNext " + e + ", thread: " + Thread.currentThread().getName()))
		    //Happens on Computation Scheduler
		    .observeOn(Schedulers.computation())
		    .filter(s -> s.matches("[0-9]+"))
		    .map(Integer::valueOf)
		    .reduce((total, next) -> total + next)
		    .subscribe(i -> System.out.println("\nVal: " + i + ", thread: " + Thread.currentThread().getName()));
		
		Thread.sleep(1000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new ObserveOn_();

	}

}



