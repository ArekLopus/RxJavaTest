package rx.flowable.create;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

//-The Flowable also has a toObservable() operator, which will turn a Flowable<T> into an Observable<T>.
//-This can be helpful in making a Flowable usable in an Observable chain, especially with operators such as flatMap().
//-If Observable<String> had much more than five emissions (such as 1,000 or 10,000), then it would probably
// be better to turn that into a Flowable instead of turning the flat-mapped Flowable into an Observable.
//-Even if you call toObservable(), the Flowable will still leverage backpressure upstream. But at the point it becomes an Observable, the
// downstream will no longer be backpressured and will request a Long.MAX_VALUE number of emissions. This may be fine as long as no more
// intensive operations or concurrency changes happen downstream and the Flowable operations upstream constrains the number of emissions.
//-But typically, when you commit to using a Flowable, you should strive to make your operations remain Flowable.
public class Flowable_toObservable {

	public Flowable_toObservable() throws InterruptedException {
		
		Flowable<Integer> integers = Flowable.range(1, 5)
		    .subscribeOn(Schedulers.computation());
		
		Observable.just("One","Two","Three","Four")
		    .flatMap(s -> integers.map(i -> i + "-" + s).toObservable())
		    .subscribe(System.out::println);
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_toObservable();

	}

}
