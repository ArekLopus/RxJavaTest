package rx.abc;

import java.util.concurrent.ExecutionException;

import io.reactivex.Maybe;
import io.reactivex.Single;

//-Converts this Single into a Maybe.
public class Single_toMaybe {

	public Single_toMaybe() throws InterruptedException, ExecutionException {
		
		Maybe<String> maybe = Single.just("One").toMaybe();
		
		maybe.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) throws Exception, ExecutionException {
		new Single_toMaybe();

	}

}
