package rx.flowable.subscriber;

import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-If you want your Subscriber to establish an explicit backpressured relationship with the operator preceding it,
// you will need to micromanage the request() calls. 
//-Lets say you want Subscriber to request 40 emissions initially and then 20 emissions at a time after that.

//-Note that the source is still emitting 128 emissions initially and then still pushes 96 emissions at a time.
//-But our Subscriber received only 40 emissions, as specified, and then consistently calls for 20 more. 
//-The request() calls in our Subscriber only communicate to the immediate operator upstream to it, which is map().
//-The map() operator likely relays that request to observeOn(), which is caching items and only flushing out 40 and then 20,
// as requested by the Subscriber. When its cache gets low or clears out, it will request another 96 from the upstream.

//-Warning: you should not rely on these exact numbers of requested emissions, such as 128 and 96. These are an internal
// implementation we happen to observe, and these numbers may be changed to aid further implementation optimizations in the future.

//-Just keep in mind that the request() calls do not go all the way upstream. They only go to the preceding operator,
// which decides how to relay that request upstream.
public class Subscriber_impl2 {

	public Subscriber_impl2() throws InterruptedException {
		
		Subscriber<Long> subscriber = new Subscriber<Long>() {
	        
			Subscription subscription;
	        AtomicInteger count = new AtomicInteger(0);
	        
	        @Override
	        public void onSubscribe(Subscription subscription) {
	            this.subscription = subscription;
	            System.out.println("Requesting 40 items!");
	            subscription.request(40);
	        }
	        @Override
	        public void onNext(Long s) {
	            try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            System.out.println("Subscriber received " + s);
	            if (count.incrementAndGet() % 20 == 0 && count.get() >= 40)
	                System.out.println("Requesting 20 more!");
	                subscription.request(20);
	            }
	        @Override
	        public void onError(Throwable e) {
	            e.printStackTrace();
	        }
	        @Override
	        public void onComplete() {
	            System.out.println("Done!");
	        }
		};
		
		Flowable.range(1,1000)
	    	.doOnNext(s -> System.out.println("Source pushed " + s))
	    	.observeOn(Schedulers.io())
	    	.map(i -> RxUtils.hardWork(i))
	    	.subscribe(subscriber);
		
		Thread.sleep(200000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Subscriber_impl2();

	}

}
