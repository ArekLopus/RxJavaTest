package rx.observable.create;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;
import rx.utils.RxUtils;

//-Provides an API (via a cold Observable) that bridges the reactive world with the callback-style world. 
public class Observable_create {

	public Observable_create() {
		
		boolean test = ThreadLocalRandom.current().nextBoolean();
		
		Observable.<String>create(emitter -> {
			try {
				if (emitter.isDisposed())
		            return;
				emitter.onNext("One");
				emitter.onNext("Two");
				if (test)throw new IllegalArgumentException("Unlucky");
				emitter.onNext("Three");
				emitter.onNext("Four");
				emitter.onComplete();
			} catch (Exception e) {
				emitter.onError(e);
			}
			
		}).subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_create();

	}

}
