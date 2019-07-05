package rx.flowable.parallel_flowable;

import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

//-Merges the values from each 'rail' in a round-robin or same-order fashion andexposes it as a regular Publisher sequence, running with a default prefetch valuefor the rails. 
//-This operator uses the default prefetch size returned by Flowable.bufferSize(). 

//-sequential does not operate by default on a particular Scheduler.
public class Flowable_sequential {

	public Flowable_sequential() {
		
		
		ParallelFlowable<String> source = Flowable.range(1, 10)
		  .parallel()
		  .runOn(Schedulers.computation())
		  .map(v -> (v * v) + ", thread: " + Thread.currentThread().getName() );
		
		source
		  .sequential()
		  .blockingSubscribe(System.out::println);
		
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Flowable_sequential();

	}

}
