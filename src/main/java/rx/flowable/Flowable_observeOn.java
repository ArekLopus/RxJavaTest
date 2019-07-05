package rx.flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

//-Modifies a Publisher to perform its emissions and notifications on a specified Scheduler, asynchronously
// with a bounded buffer of bufferSize() slots. 
public class Flowable_observeOn {

	public Flowable_observeOn() throws InterruptedException {
		
		Flowable.range(1, 200)
    		.doOnNext(e -> System.out.println("Produced: " + e + ", thread: " + Thread.currentThread().getName()))
	    	.observeOn(Schedulers.io())
			
	    	.subscribeOn(Schedulers.computation())
	    	.subscribe(i -> {
	    		Thread.sleep(500);
	    		System.out.println("Received: " + i + ", thread: " + Thread.currentThread().getName());
	    	});
		
		
		Thread.sleep(2000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Flowable_observeOn();

	}
	
}
