package rx.operators_blocking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.reactivex.Observable;

//-If this Observable completes after emitting a single item, return that item, otherwisethrow a NoSuchElementException.
//-blockingSingle() is a similar blocking operator to blockingFirst(),
// which expects only a single item to be emitted, but throws an error if there are more.
//-Exception if more than 1 element:
//	java.lang.IllegalArgumentException: Sequence contains more than one element!
public class BlockingSingle {

	@Test
	public void test() {
	    
		Observable<String> source = Observable.just("One");
	    String isSingle = source
	    	.blockingSingle();
	    
	    assertTrue(isSingle.equals("One"));
	}

}
