package rx.operators.error_recovery;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;

//-Returns an Observable that mirrors the source ObservableSource, resubscribing to it if it calls onError
// and the predicate returns true for that specific exception and retry count. 
//-retry does not operate by default on a particular Scheduler.

//-Recovers after 1st try:		Was Error: Bad false	Received: true
//-Does not recover				Was Error: Bad false	ERROR: java.lang.IllegalStateException: Bad false
public class Retry_bipredicate {
	
	public Retry_bipredicate() {
		
		Observable.create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean)
				em.onNext(nextBoolean);
			else
				em.onError(new IllegalStateException("Bad false"));
		})
		// Tries 1 time if Error. If true then returns true if false calls onError. 
		.retry(1, e -> {
			System.out.println("Was Error: " + e.getMessage());
			return e != null ? true : false; 
		})
    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Retry_bipredicate();

	}

}
