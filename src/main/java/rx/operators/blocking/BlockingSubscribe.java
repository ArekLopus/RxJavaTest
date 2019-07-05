package rx.operators.blocking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;

//-Subscribes to the source and calls the given callbacks on the current thread. 
//-Without blockingSubscribe() main thread will finish before test can finish (hitCount == 0)
public class BlockingSubscribe {

	public BlockingSubscribe() {
	    
		AtomicInteger hitCount = new AtomicInteger();
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
				.take(5);
	    
		source.blockingSubscribe(i -> hitCount.incrementAndGet());
		//source.subscribe(i -> hitCount.incrementAndGet());
		
		System.out.println(hitCount.get());
	    
	    
	    System.out.println("--- Main Thread Finished ---");
	}
	   	
	public static void main(String[] args) {
		new BlockingSubscribe();

	}

}
