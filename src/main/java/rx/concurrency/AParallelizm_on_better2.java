package rx.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Creating an Observable (Parallelism_on) for each emission might create some unwanted overhead.
//-There is a leaner way to achieve parallelization, although it has a few more moving parts.
//-We should split the source Observable into a fixed number of Observables where emissions are evenly divided and distributed through each one.
// Then, we can parallelize and merge them with flatMap().
//-Since I have 4 cores on my computer, maybe it would be ideal that I have 4 Observables for 4 streams of calculations.
//-We can achieve this using a groupBy().
//-We keep incrementing AtomicInteger for each emission and divide the result by the numbers of cores,
// but return the remainder, which will always be a number between 0 and 3.
//-We can also use an io() or newThread() scheduler since we have already constrained the number of workers to the number of cores,
// simply by constraining the number of GroupedObservables.
public class AParallelizm_on_better2 {
	
	public AParallelizm_on_better2() throws InterruptedException {
		
		long start = System.currentTimeMillis();
		int cores = Runtime.getRuntime().availableProcessors();
		AtomicInteger assigner = new AtomicInteger(0);
		
		Observable<GroupedObservable<Integer, Integer>> grouped = Observable.range(1,10)
		    .groupBy(i -> assigner.incrementAndGet() % cores);		// 1, 2, 3, 0, 1, 2, 3, 0, 1, 2
		
		// Lets see the groups
		grouped
			.flatMapSingle(grp -> grp.toMultimap(i -> grp.getKey()))
			.subscribe(System.out::println);
		
		grouped
			.flatMap(grp -> grp.observeOn(Schedulers.io())
		                   	   .map(i2 -> RxUtils.hardWork(500))
		    )
		    .subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName() + ", time (ms) " + (System.currentTimeMillis() - start)));
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new AParallelizm_on_better2();

	}

}