package rx.concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rx.utils.RxUtils;

public class Observable_concurent {

	public Observable_concurent() throws InterruptedException {
		
		Observable.just("One", "Two", "Three", "Four")
			.observeOn(Schedulers.io())
			//.subscribeOn(Schedulers.computation())
		    .subscribe(RxUtils::consumeStringWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint, () -> System.out.println("Op Completed"));
		
		Thread.sleep(200);
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) throws Exception {
		new Observable_concurent();

	}

}
