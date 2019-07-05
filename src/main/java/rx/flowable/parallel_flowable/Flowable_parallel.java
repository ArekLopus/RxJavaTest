package rx.flowable.parallel_flowable;

import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

//-Parallelizes the flow by creating multiple 'rails' (equal to # of CPUs) and dispatches the upstream items to them in a round-robin fashion. 
//-Note that the rails don't execute in parallel on their own and one needs to apply
// ParallelFlowable.runOn(Scheduler) to specify the Scheduler where each rail will execute. 
//-To merge the parallel 'rails' back into a single sequence, use ParallelFlowable.sequential(). 

//-parallel does not operate by default on a particular Scheduler.
public class Flowable_parallel {

	public Flowable_parallel() {
		
		
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
		new Flowable_parallel();

	}

}
