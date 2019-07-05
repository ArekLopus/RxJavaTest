package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Remember, serialization (emitting items one at a time) only needs to happen on the same Observable.
// The flatMap() operator will merge multiple Observables derived off each emission even if they are concurrent.
//-We created a Observable off each emission, used subscribeOn() to emit it on the computation Scheduler,
// and then performed the hardWork(), which will occur on one of the computation threads.
// Each instance will request its own thread from the computation Scheduler, and
// flatMap() will merge all of them safely back into a serialized stream.
//-flatMap() will only let one thread out of it at a time to push emissions downstream,
// which maintains that the Observable contract demanding emissions stays serialized.
public class AParallelizm_on_ {
	
	public AParallelizm_on_() throws InterruptedException {
		
		long start = System.currentTimeMillis();
		
		Observable.range(1,10)
			.flatMap(e -> Observable.just(e)
									.subscribeOn(Schedulers.computation())
									.map(i -> RxUtils.hardWork(500))
			)
			.subscribe(i -> System.out.println("Val: " + i + ", thread: " + Thread.currentThread().getName() + ", time (ms) " + (System.currentTimeMillis() - start)));
		
		Thread.sleep(3000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new AParallelizm_on_();

	}

}



