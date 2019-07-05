package rx.transformers;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;

//-When you implement your own ObservableTransformer, you might want to create a FlowableTransformer counterpart as well.
//-This way, you can use your operator on both Observables and Flowables.
//-The FlowableTransformer is not much different from ObservableTransformer. Of course, it will support backpressure since it is composed with
// Flowables. Otherwise, it is pretty much the same in its usage except that you obviously pass it to compose() on a Flowable, not Observable.
public class FlowTransformer {

	public FlowTransformer() {
		
		Flowable.just("One", "Two", "Three", "Four")
	    	.compose(toJsonArray())
	    	.subscribe(System.out::println);
		
		
		Flowable.range(1, 10)
    		.compose(toJsonArray())
    		.subscribe(System.out::println);
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	

	// JsonArrayBuilder cant add generic types, first need to convert to List and create array builder from this list (accepts Colleciot<?>).
	public static <T> FlowableTransformer<T , JsonArray> toJsonArray() {
		FlowableTransformer<T,JsonArray> trans = upstream ->
			upstream
				.toList()										
				.map(Json::createArrayBuilder).toObservable()
				.collect(Json::createArrayBuilder, JsonArrayBuilder::addAll)
				.map(JsonArrayBuilder::build)
				.toFlowable(); 							// collect returns Single, we MUST turn it into Flowable
		 return trans;
    }
	
	
	// Doesnt work for generic type, JsonArrayBuilder cant add generic type.
//	public static <T> ObservableTransformer<T, JsonArray> toJsonArray() {
//    return new ObservableTransformer<T, JsonArray>() {
//        @Override
//        public ObservableSource<JsonArray> apply(Observable<T> upstream) {
//            return upstream.collect(Json::createArrayBuilder, JsonArrayBuilder::add)
//                                     .map(JsonArrayBuilder::build)
//                                     .toObservable(); 		// collect returns Single, we MUST turn it into Observable
//        }
//    };
//}

	
	public static void main(String[] args) {
		new FlowTransformer();

	}

}
