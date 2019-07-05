package rx.flowable.subscriber;

import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriberImpl<T> implements Subscriber<T> {

	private Subscription subscription;
	private AtomicInteger count = new AtomicInteger(0);
	private int timeToSleep = 50;
	private int initialItemsRequest = 40;
	private int itemsRequest = 20;
	
	public SubscriberImpl() {}
	
	public SubscriberImpl(int timeToSleep) {
		this.timeToSleep = timeToSleep;
	}
	
	public SubscriberImpl(int initialItemsRequest, int itemsRequest, int timeToSleep) {
		this.initialItemsRequest = initialItemsRequest;
		this.itemsRequest = itemsRequest;
		this.timeToSleep = timeToSleep;
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println("Requesting "+ this.initialItemsRequest +" items!");
		subscription.request(initialItemsRequest);
	}

	@Override
	public void onNext(T s) {
		try {
			Thread.sleep(timeToSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Subscriber received " + s);
		if (count.incrementAndGet() % this.itemsRequest == 0 && count.get() >= this.initialItemsRequest)
			System.out.println("Requesting " + this.itemsRequest + " more!");
		subscription.request(this.itemsRequest);
	}

	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("Done!");
	}

}
