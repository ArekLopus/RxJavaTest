package rx.operators.blocking;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-Returns an Iterable that blocks until this Observable emits another item, then returns that item. 

//-The blockingNext() will return an iterable and block each iterator's next() request until the next value is provided.
//-Emissions that occur after the last fulfilled next() request and before the current next() are ignored.
public class BlockingNext {

	public BlockingNext() {
	    
		Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS)
		    .take(100);
		
		Iterable<Long> iterable = source
			.blockingNext();
		
		for (Long i: iterable) {
			System.out.println(i);
		}
	    
	}
	
	public static void main(String[] args) {
		new BlockingNext();

	}

}
