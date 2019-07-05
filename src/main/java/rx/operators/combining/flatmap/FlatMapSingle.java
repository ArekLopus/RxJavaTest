package rx.operators.combining.flatmap;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

//-map transform one event to another. flatMap transform one event to zero or more events.
//-FlatMap behaves very much like map, the difference is that the function it applies returns an observable itself
public class FlatMapSingle {

	public FlatMapSingle() throws InterruptedException {
		
		String text = "One Two Three Four Five";
        
		Single.just(text)
            .flatMapPublisher(s -> Flowable.fromArray(s.split(" ")))
            .subscribe(System.out::println);
		System.out.println();
		
		Single.just(text)
        	.flatMapObservable(s -> Observable.fromArray(s.split(" ")))
        	.subscribe(System.out::println);
		System.out.println();
		
		Single.just(text)
    		.flatMap(s -> Observable.fromArray(s.split(" ")).toList())
    		.subscribe(System.out::println);
		
		Single.just(text)
			.flatMapMaybe(s -> Maybe.just("One"))
			.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new FlatMapSingle();

	}

}
