package rx.abc;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import io.reactivex.Flowable;
import io.reactivex.Observable;

//-Returns a Future representing the single value emitted by this Single. 
public class Single_toFuture {

	public Single_toFuture() throws InterruptedException, ExecutionException {
		
		Future<List<String>> future = Observable.just("One", "Two", "Three", "Four").toList()
			.toFuture();
		
		Future<List<String>> future2 = Flowable.just("One", "Two", "Three", "Four").toList()
			.toFuture();
		
		System.out.println(future.get());
		System.out.println(future2.get());
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception, ExecutionException {
		new Single_toFuture();

	}

}
