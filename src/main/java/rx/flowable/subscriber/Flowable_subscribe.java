package rx.flowable.subscriber;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-Just like an Observer, the quickest way to create a Subscriber is to pass lambda arguments to subscribe(),
public class Flowable_subscribe {

	public Flowable_subscribe() throws InterruptedException {
		
		Flowable.range(1,1000)
	    	.doOnNext(s -> System.out.println("Source pushed " + s))
	    	.observeOn(Schedulers.io())
	    	.map(i -> RxUtils.hardWork(i))
	    	.subscribe(s -> System.out.println("Subscriber received " + s), Throwable::printStackTrace, () -> System.out.println("Done!"));
		
		Thread.sleep(300000);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Flowable_subscribe();

	}

}
