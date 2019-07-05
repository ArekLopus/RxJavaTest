package rx.transformers;

import java.util.Collection;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.SingleTransformer;

//	Custom Transformers and operators for Singles, Maybes, and Completables
//-There are Transformer and operator counterparts for Single, Maybe, and Completable.
//-When you want to create an Observable or Flowable operator that yields Single, you might find it easier to convert
// it back into an Observable/Flowable by calling its toObservable() or toFlowable() operators. This also applies to Maybe. 
//-If on some rare occasion you need to create a Transformer or operator specifically to takea Single and transform it into another Single,
// you will want to use SingleTransformer or SingleOperator.
//-Maybe, Completable have counterparts with MaybeTransformer/MaybeOperator and CompletableTransformer/CompletableOperator.
//-The implementation of apply() for all of these should largely be the same experience, and you will use SingleObserver, MaybeObserver,
// and CompletableObserver to proxy the upstream and downstream.
public class SingleTransformerTest {

	public SingleTransformerTest() {

		Observable.just("One", "Two", "Three", "Four").toList()
	    	.compose(toUnmodifiable())
	    	.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	
	public static <T> SingleTransformer<Collection<T>, Collection<T>> toUnmodifiable() {
	    return singleObserver ->
	        singleObserver.map(Collections::unmodifiableCollection);
	}
	
	
	public static void main(String[] args) {
		new SingleTransformerTest();
	}

}
