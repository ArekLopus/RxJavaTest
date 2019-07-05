package rx.flowable.create;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-There is another way that you can implement BackpressureStrategy against a source that has no notion of backpressure.
//-You can turn an Observable into Flowable easily by calling its toFlowable() operator, which accepts a BackpressureStrategy as an arg.
//-The Observable has no notion of backpressure, so it is going to push items as quickly as it can regardless if the downstream can keep up.
// But toFlowable(), with a buffering strategy, will act as a proxy to backlog the emissions when the downstream cannot keep up.

//-Note that toFlowable(), with a buffering strategy, is going to have an unbounded queue, which can cause an OutOfMemoryError.
//-It would be better to use Flowable.range() in the first place, but sometimes, you may only be provided with an Observable.
public class Flowable_toFlowable {

	public Flowable_toFlowable() throws InterruptedException {
		
		Observable<Integer> source = Observable.range(1,1000);
		source.toFlowable(BackpressureStrategy.BUFFER)
		    .observeOn(Schedulers.io())
		    .subscribe(System.out::println);
		
		Thread.sleep(10000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_toFlowable();

	}

}
