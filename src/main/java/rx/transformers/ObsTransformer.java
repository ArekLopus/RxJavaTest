package rx.transformers;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

//-It is redundant to invoke twice, so you can compose these operators into a single operator by implementing ObservableTransformer<T,R>.
//-This type has an apply() method that accepts an Observable<T> upstream and returns an Observable<R> downstream.
//-In your implementation, you can return an Observable chain that adds on any operators to the upstream,
// and after those transformations, it returns an Observable<R>.
public class ObsTransformer {

	public ObsTransformer() {
		
		Observable.just("One", "Two", "Three", "Four")
	    	.compose(toJsonArray())
	    	.subscribe(System.out::println);
		
		
		Observable.range(1, 10)
    		.compose(toJsonArray())
    		.subscribe(System.out::println);
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	

	
	// JsonArrayBuilder cant add generic types, first need to convert to List and create array builder from this list (accepts Colleciot<?>).
	public static <T> ObservableTransformer<T , JsonArray> toJsonArray() {
		ObservableTransformer<T,JsonArray> trans = upstream ->
			upstream
				.toList()
				.map(Json::createArrayBuilder).toObservable()
				.collect(Json::createArrayBuilder, JsonArrayBuilder::addAll)
				.map(JsonArrayBuilder::build)
				.toObservable(); 							// collect returns Single, we MUST turn it into Observable
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
		new ObsTransformer();

	}

}
