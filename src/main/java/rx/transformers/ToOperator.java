package rx.transformers;

import java.util.List;

import io.reactivex.Observable;

//-On rare occasions, you may find yourself having to pass an Observable to another API that converts it into a proprietary type.
//-This can be done simply by passing an Observable as an argument to a factory that does this conversion.
// However, this does not always feel fluent, and this is where the to() operator comes in.
//-That can be done with the to() operator, which simply accepts an Function<Observable<T>,R> to turn an Observable<T> into any arbitrary R type.
//-When you are dealing with proprietary non-Rx types that can be built off Rx Observabes and Flowables,
// this is a handy utility to maintain the fluent Rx style, especially when interoperating with binding frameworks.
public class ToOperator {

	public ToOperator() {
		
        //List<String> list = Observable.interval(1, TimeUnit.SECONDS)
		List<String> list = Observable.just("One", "Two", "Three", "Four")
        	.map(i -> i.toString())
        	.to(ToOperator::toList);
       
        System.out.println(list);
		
        
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	static <T> List<T> toList(Observable<T> obs) {
		return obs.toList().blockingGet();
	}
	
	
	public static void main(String[] args) {
		new ToOperator();

	}

}
