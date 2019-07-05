package rx.operators.error_recovery;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;

//-Retries until the given stop function returns true. 
//-retryUntil does not operate by default on a particular Scheduler.
public class Retry_until {
	
	private int checker = 0;
	
	public Retry_until() {
		
		Observable.create(em -> {
			boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();
			if(nextBoolean) {
				checker = 0;
				System.out.println(checker);
				em.onNext(nextBoolean);
			} else {
				checker = 1;
				System.out.println(checker);
				em.onError(new IllegalStateException("Bad false"));
			}
				
		})
    	.retryUntil( () -> !(checker > 0) )
    	.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Retry_until();

	}

}
