package rx.operators.error_recovery;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;

//-Retries the current Observable if the predicate returns true. 
//-retry does not operate by default on a particular Scheduler.
public class Retry_predicate {
	
	public Retry_predicate() {
		
		Observable.create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean)
				em.onNext(nextBoolean);
			else
				em.onError(new IllegalStateException("Bad false"));
		})
		.retry(e -> {
			System.out.println("Was Error: " + e.getMessage());
			return e != null ? true : false; 
		})
    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Retry_predicate();

	}

}
