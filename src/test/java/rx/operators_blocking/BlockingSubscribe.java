package rx.operators_blocking;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import io.reactivex.Observable;

//-Subscribes to the source and calls the given callbacks on the current thread. 
//-Without blockingSubscribe() main thread will finish before test can finish (hitCount == 0)
public class BlockingSubscribe {

	@Test
	public void test() {
	    
		AtomicInteger hitCount = new AtomicInteger();
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MILLISECONDS)
				.take(5);
	    
		source.blockingSubscribe(i -> hitCount.incrementAndGet());
		//source.subscribe(i -> hitCount.incrementAndGet());
		
	    assertTrue(hitCount.get() == 5);
	
	}

}
