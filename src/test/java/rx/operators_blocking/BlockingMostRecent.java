package rx.operators_blocking;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Iterable that always returns the item most recently emitted by this Observable. 
//-The blockingMostRecent() is similar to blockingLatest(), but it will re-consume the latest value repeatedly
// for every next() call from the iterator even if it was consumed already.
//-It also requires a defaultValue argument so it has something to return if no value is emitted yet.
public class BlockingMostRecent {

	public BlockingMostRecent() {
	    
		Observable<Long> source = Observable.interval(10, TimeUnit.MILLISECONDS)
		    .take(5);
		
		Iterable<Long> iterable = source
			.blockingMostRecent(-1L);
		
		for (Long i: iterable) {
			System.out.println(i);
		}
		
	}
	
	public static void main(String[] args) {
		new BlockingMostRecent();

	}
	
}
