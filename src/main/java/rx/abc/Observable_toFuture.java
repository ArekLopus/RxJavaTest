package rx.abc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import io.reactivex.Flowable;


public class Observable_toFuture {

	public Observable_toFuture() throws InterruptedException, ExecutionException {
		
		Future<String> future = Flowable.just("One").toFuture();
		
		System.out.println(future.get());
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception, ExecutionException {
		new Observable_toFuture();

	}

}
