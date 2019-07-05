package rx.transformers;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

//-Note that a single instance of AtomicInteger is shared between both subscriptions, which means its state was shared as well.
// On the second subscription, instead of starting over at 0, it picks up at the index left by the previous subscription and starts
// at index 4 since the previous subscription ended at 4.
//-Unless you have some stateful behaviors you are deliberately implementing, this is probably an unwanted side-effect that can result
// in maddening bugs. Constants are usually fine, but a mutable shared state between subscriptions is often something you want to avoid.
//-A quick and easy way to create a new resource (such as AtomicInteger) for each subscription is to wrap everything in Observable.defer(),
// including the AtomicInteger instance. This way, a new AtomicInteger is created each time with the returned indexing operations.
//-You can also create an AtomicInteger within Observable.fromCallable() and use flatMap() on it to the Observable that uses it.
//-In this particular example, you can also use Observable.zip() or zipWith() with Observable.range(). Since this is a pure Rx approach as well,
// no state will be shared between multiple subscribers, and this will also solve our problem.
public class TransformersSharedState {

	public TransformersSharedState() {
		
		Observable<String> strings = Observable.just("One", "Two", "Three", "Four")
				.compose(withIndex());
		
		strings.subscribe(v -> System.out.println("Subscriber 1: " + v));
		strings.subscribe(v -> System.out.println("Subscriber 2: " + v));
		System.out.println();
		
		
		Observable<String> strings2 = Observable.just("One", "Two", "Three", "Four")
				.compose(withIndexNotShared());
		strings2.subscribe(v -> System.out.println("Subscriber 1: " + v));
		strings2.subscribe(v -> System.out.println("Subscriber 2: " + v));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	static <T> ObservableTransformer<T, String> withIndex() {
	    final AtomicInteger indexer = new AtomicInteger(0);
	    return upstream -> upstream.map(v -> " counter: " + indexer.getAndIncrement() + ", Val: " + v);
	}
	
	static <T> ObservableTransformer<T,String> withIndexNotShared() {
	    return upstream -> Observable.defer(() -> {
	        AtomicInteger indexer = new AtomicInteger(0);
	        return upstream.map(v -> " counter: " + indexer.getAndIncrement() + ", Val: " + v);
	    });
	}

	
	public static void main(String[] args) {
		new TransformersSharedState();

	}

}
