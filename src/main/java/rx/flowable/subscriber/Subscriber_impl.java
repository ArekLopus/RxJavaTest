package rx.flowable.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

//-You can implement your own Subscriber which, of course, has the onNext(), onError(), and onComplete() methods as well as onSubscribe().
//-This is not as straightforward as impl an Observer because you need to call request() on Subscription to request emissions at the right moments.
//-The quickest and easiest way to implement a Subscriber is to have the onSubscribe() call request(Long.MAX_VALUE) on Subscription,
// which essentially tells the upstream "give me everything now".
//-Even though the operators preceding Subscriber will request emissions at their own backpressured pace, no backpressure will exist between
// the last operator and the Subscriber.
//-This is usually fine since the upstream operators will constrain the flow anyway.
public class Subscriber_impl {

	public Subscriber_impl() throws InterruptedException {
		
		Subscriber<Long> subscriber = new Subscriber<Long>() {
	        
	    	@Override
	        public void onSubscribe(Subscription subscription) {
	            subscription.request(Long.MAX_VALUE);
	        }
	        @Override
	        public void onNext(Long s) {
	            try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            System.out.println("Subscriber received " + s);
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
	    	.map(i -> RxUtils.hardWork(1000))
	    	.subscribe(subscriber);
		
		Thread.sleep(20000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Subscriber_impl();

	}

}
