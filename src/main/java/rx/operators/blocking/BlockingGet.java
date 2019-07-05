package rx.operators.blocking;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

//-Waits in a blocking fashion until the current Single signals a success value (which is returned) or an exception (which is propagated). 
//-Maybe and Single do not have blockingFirst() since there can only be one element at most.
//-Logically, for a Single and Maybe, it is rather the only element, so the equivalent operator is blockingGet().
public class BlockingGet {

	public BlockingGet() {
	    
	    Observable<String> source = Observable.just("One", "Two", "Three", "Four", "Five");
	    
	    List<String> allWithLengthFour = source.filter(s -> s.length() == 4)
	        .toList()
	        .blockingGet();
	    
	    System.out.println(allWithLengthFour.equals(Arrays.asList("Four", "Five")));
	    
	    
	    System.out.println("--- Main Thread Finished ---");
	}
	
	public static void main(String[] args) {
		new BlockingGet();

	}

}
