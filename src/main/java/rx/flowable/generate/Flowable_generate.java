package rx.flowable.generate;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Flowable.generate() is only allowed to generate one event at a time (or complete a stream).
// Backpressure mechanism transparently figures out how many events it needs at the moment.
// generate() is called appropriate number of times, for example 128 times in case of observeOn().

//-The first argument to generate() is an initial state (factory), 0L in our case.
//-Now every time a subscriber or any downstream operator asks for some number of events, the lambda expression is invoked.
// Its responsibility is to call onNext() at most once (emit at most one event) somehow based on supplied state. 
// When lambda is invoked for the first time the state is equal to initial value 0L. However we are allowed to modify the state
// and return its new value. In this example we increment long so that subsequent invocation of lambda expression receives state = 1L.
public class Flowable_generate {
	
	public Flowable_generate() throws InterruptedException {
		
		Flowable<Long> naturalNumbers = Flowable.generate(() -> 0L, (state, emitter) -> {
	  	emitter.onNext(state);
	    	return state + 1;
		});
		
		naturalNumbers
	    	.subscribeOn(Schedulers.computation())
	    	.doOnNext(i -> System.out.println("Emitting " + i))
	    	.observeOn(Schedulers.io())
	    	.subscribe(i -> {
	    		Thread.sleep(500);
	    		System.out.println("Received " + i);
	    	});
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	

	public static void main(String[] args) throws Exception {
		new Flowable_generate();

	}

}
