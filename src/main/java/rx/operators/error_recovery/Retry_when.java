package rx.operators.error_recovery;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;

//-Returns an Observable that emits the same values as the source ObservableSource with the exception of an onError.
// An onError notification from the source will result in the emission of a Throwable item to the ObservableSource provided
// as an argument to the notificationHandlerfunction. If that ObservableSource calls onComplete or onError then retry will
// call onComplete or onError on the child subscription. Otherwise, this ObservableSource willresubscribe to the source ObservableSource. 
//-retryWhen does not operate by default on a particular Scheduler.
public class Retry_when {

	public Retry_when() {
		
		Observable<Boolean> source = Observable.create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean) {
				em.onNext(nextBoolean);
			} else {
				em.onError(new IllegalStateException("Bad false"));
			}
				
		});
    	
		source
			.retryWhen( errors -> errors.flatMap(error -> {
				// For IllegalStateException we retry using the same source.
				if (error instanceof IllegalStateException) {
					System.out.println("Instance of IllegalStateException");
					return source;
				}
				// For anything else we don't retry
				return Observable.error(error);
    	  	}))
    		.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Retry_when();

	}

}
