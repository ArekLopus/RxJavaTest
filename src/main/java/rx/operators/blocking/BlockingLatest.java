package rx.operators.blocking;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Iterable that returns the latest item emitted by this Observable, waiting if necessary for one to become available. 
//-The iterable from blockingLatest() does not wait for the next value, but requests the last emitted value.
// Any values before that which were not captured are forgotten.
//-It will not reconsume the latest value if the iterator's next() consumed it previously and will block until the next one comes.
public class BlockingLatest {

	public BlockingLatest() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS)
			.take(100);
		
		Iterable<Long> iterable = source
			.blockingLatest();
		
		for (Long i: iterable) {
			System.out.println(i);
		}
		
	}
	
	public static void main(String[] args) {
		new BlockingLatest();

	}
	
}
