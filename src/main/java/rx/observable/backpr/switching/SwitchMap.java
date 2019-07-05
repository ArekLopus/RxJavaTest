package rx.observable.backpr.switching;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-switchMap() is like flatMap() except that it will cancel any previous Observables that were processing and only chase after the latest one.
//-This can be helpful in many situations to prevent redundant or stale work and is especially effective in user interfaces where rapid user
// inputs create stale requests. You can use it to cancel DB queries, web requests, and other expensive tasks and replace it with a new task.

//-Say we want to run a process every 2 seconds, but we want to cancel (or more technically, dispose()) previous instances of the process
// and only run the latest one. This is easy to do with switchMap(). 

//-We create Observable.interval(), emitting every 2 seconds, and then we use switchMap() on it to the Observable we want to process
// (which in this case is processStrings). 
//-Every 2 seconds, the emission going into switchMap() will promptly dispose of the currently processing Observable
// (if there are any) and then emit from the new Observable it maps to.
//-To prove that dispose() is being called, we will put doOnDispose() on the Observable inside switchMap() to display a message:
public class SwitchMap {

	public SwitchMap() throws InterruptedException {
		
		//delay each String to emulate an intense calculation
		Observable<String> processStrings = Observable
			.just("One", "Two", "Three", "Four", "five", "Six", "Seven", "Eight")
			.concatMap(s -> Observable.just(s).delay(500, TimeUnit.MILLISECONDS));
		
		// run processStrings every 2 seconds, and kill each previous instance to start next
		Observable.interval(2, TimeUnit.SECONDS)
		    .switchMap(i -> processStrings.doOnDispose(() -> System.out.println("Disposing! Starting next")))
		    .subscribe(System.out::println);
		
		Thread.sleep(10000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new SwitchMap();

	}

}