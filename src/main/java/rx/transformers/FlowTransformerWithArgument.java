package rx.transformers;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;

//-When you implement your own ObservableTransformer, you might want to create a FlowableTransformer counterpart as well.
//-This way, you can use your operator on both Observables and Flowables.
//-The FlowableTransformer is not much different from ObservableTransformer. Of course, it will support backpressure since it is composed with
// Flowables. Otherwise, it is pretty much the same in its usage except that you obviously pass it to compose() on a Flowable, not Observable.
public class FlowTransformerWithArgument {

	public FlowTransformerWithArgument() {
		
		Flowable.just("One", "Two", "Three", "Four")
	    	.compose(FlowTransformerWithArgument.toStringBuilderWithSeparator("-"))
	    	.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	

	public static FlowableTransformer<String, String> toStringBuilderWithSeparator(String separator) {
		
		FlowableTransformer<String, String> trans = upstream -> upstream
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
				.toFlowable(); 				// collect returns Single, we MUST turn it into Flowable
		
		return trans;
	}

	
	public static void main(String[] args) {
		new FlowTransformerWithArgument();

	}

}
