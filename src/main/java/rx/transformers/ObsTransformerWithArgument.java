package rx.transformers;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

//-It is redundant to invoke twice, so you can compose these operators into a single operator by implementing ObservableTransformer<T,R>.
//-This type has an apply() method that accepts an Observable<T> upstream and returns an Observable<R> downstream.
//-In your implementation, you can return an Observable chain that adds on any operators to the upstream,
// and after those transformations, it returns an Observable<R>.
public class ObsTransformerWithArgument {

	public ObsTransformerWithArgument() {
		
		Observable.just("One", "Two", "Three", "Four")
	    	.compose(ObsTransformerWithArgument.toStringBuilderWithSeparator("-"))
	    	.subscribe(System.out::println);
		
		
//		Observable.range(1, 10)
//			.compose(toStringBuilderWithSeparator("-"))			// Cant be used for Integers
//			.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	

	public static ObservableTransformer<String, String> toStringBuilderWithSeparator(String separator) {
		
		ObservableTransformer<String, String> trans = upstream -> upstream
				.collect(StringBuilder::new, (b, s) -> {
					if (s.length() == 0) {
						b.append(s);
					} else {
						if(b.length() == 0)
							b.append(s);
						else
							b.append(separator).append(s);
					}
				})
				.map(StringBuilder::toString)
				.toObservable(); 				// collect returns Single, we MUST turn it into Observable
		
		return trans;
	}

	
	public static void main(String[] args) {
		new ObsTransformerWithArgument();

	}

}
