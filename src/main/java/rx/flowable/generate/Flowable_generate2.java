package rx.flowable.generate;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Flowable.generate() is only allowed to generate one event at a time (or complete a stream).
// Backpressure mechanism transparently figures out how many events it needs at the moment.
// generate() is called appropriate number of times, for example 128 times in case of observeOn().

//-The first argument to generate() is an initial state (factory), AtomicInteger(0) in our case.
//-Now every time a subscriber or any downstream operator asks for some number of events, the lambda expression is invoked.
public class Flowable_generate2 {
	
	private int upperBound = 0;
	private int lowerBound = 300;
	
	public Flowable_generate2() throws InterruptedException {
		
		Flowable<Object> generated = Flowable.generate(() -> new AtomicInteger(upperBound), (state, emitter) -> {
	        int current = state.incrementAndGet();
	        emitter.onNext(current);
	        if (current == lowerBound)
	            emitter.onComplete();
	        }
	    );
		
		generated
	    	.subscribeOn(Schedulers.computation())
	    	.doOnNext(i -> System.out.println("Emitting " + i))
	    	.observeOn(Schedulers.io())
	    	.subscribe(i -> {
	    		Thread.sleep(50);
	    		System.out.println("Received " + i);
	    	});
		Thread.sleep(50000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	

	public static void main(String[] args) throws Exception {
		new Flowable_generate2();

	}

}
