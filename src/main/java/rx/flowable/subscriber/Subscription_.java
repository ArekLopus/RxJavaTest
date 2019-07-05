package rx.flowable.subscriber;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-A Subscription represents a one-to-one lifecycle of a Subscriber subscribing to a Publisher. 
//-It can only be used once by a single Subscriber. 
//-It is used to both signal desire for data and cancel demand (and allow resource cleanup).
public class Subscription_ {

	public Subscription_() throws InterruptedException {
		
		Flowable<Long> source = Flowable.range(1,10)
	    	.doOnNext(s -> System.out.println("Source pushed " + s))
	    	.observeOn(Schedulers.io())
	    	.map(i -> RxUtils.hardWork(i));
		
		Subscription subsc = (Subscription) source
			.subscribe(s -> {
				Thread.sleep(200);
				System.out.println("Subscriber received " + s);
			}, Throwable::printStackTrace, () -> System.out.println("Done!"));
		
		Thread.sleep(1000);
		
		subsc.cancel();
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Subscription_();

	}

}
