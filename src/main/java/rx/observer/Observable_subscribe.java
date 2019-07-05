package rx.observer;

import io.reactivex.Observable;
import rx.utils.RxUtils;

// Disposable	subscribe()	- Subscribes to an ObservableSource and ignores onNext and onComplete emissions.
// Disposable	subscribe(Consumer<? super T> onNext)
// Disposable	subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError)
// Disposable	subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete)
// Disposable	subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe)
// void			subscribe(Observer<? super T> observer)		- in Observer_implement class
//-Note that passing an Observer to the subscribe() method will be void and not return a Disposable since it is assumed that
// the Observer will handle it. 
public class Observable_subscribe {

	public Observable_subscribe() {
		
		Observable<String> source = Observable.just("One", "Two", "Three", "Four");
		Observable<Integer> filtered = source.map(String::length).filter(i -> i >= 4);
		
		filtered.subscribe(RxUtils::consumeNumberWithThreadPrint, RxUtils::consumeThrowableWithThreadPrint,
				() -> System.out.println("onComplete"), s -> System.out.println("onSubscrbe: " + s));
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observable_subscribe();

	}

}
