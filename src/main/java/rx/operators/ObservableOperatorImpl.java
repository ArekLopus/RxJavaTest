package rx.operators;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;

//-To create your own ObservableOperator<Downstream,Upstream> (where Upstream is the upstream emission type
// and Downstream is the downstream emission type), you will need to implement its apply() method.
// This accepts an Observer<Downstream> observer argument and returns an Observer<Upstream>.
//-You can then use this ObservableOperator by calling it in the lift() operator in your Observable chain.
//-Inside apply(), you take the passed Observer that accepts events for the downstream.
// Since it is a single abstract method class, you can also express your ObservableOperator implementation as a lambda
//-You create another Observer (in this case, we should use a DisposableObserver that handles disposal requests for us)
// to receive emissions and events from the upstream and relay them to the downstream Observer.
//-You can manipulate the events to execute the desired logic as well as add any side-effects.
//-In this case, we simply passed the events from the upstream to the downstream untampered but track whether onNext() was called to flag if
// emissions were present. When onComplete() is called and no emissions are present, it will execute the userspecified action within onComplete().
//-It is a good idea to wrap any code that could throw runtime errors in try-catch and pass those captured errors to onError().
//-With ObservableOperator, it may seem odd that you get the downstream as an input and have to produce an Observer for the upstream as
// the output. With the map() operator, fe, the function receives the upstream value and returns the value to be emitted toward the downstream.
//-The reason for this is that code from an ObservableOperator gets executed at subscription time where the call travels from the end Observer
// (downstream) toward the source Observable (upstream).
public class ObservableOperatorImpl {

	public ObservableOperatorImpl() {

		Observable.range(1, 5)
			.lift(doOnEmpty(() -> System.out.println("Operation 1 Empty!")))
			.subscribe(v -> System.out.println("Operation 1: " + v));
		Observable.empty()
			.lift(doOnEmpty(() -> System.out.println("Operation 2 Empty!")))
			.subscribe(v -> System.out.println("Operation 2: " + v));

		
		System.out.println("--- Main Thread Finished ---");

	}
	
	public static <T> ObservableOperator<T, T> doOnEmpty(Action action) {
		
		return new ObservableOperator<T, T>() {
			
			@Override
			public Observer<? super T> apply(Observer<? super T> observer) throws Exception {
				
				return new DisposableObserver<T>() {
					boolean isEmpty = true;

					@Override
					public void onNext(T value) {
						isEmpty = false;
						observer.onNext(value);
					}

					@Override
					public void onError(Throwable t) {
						observer.onError(t);
					}

					@Override
					public void onComplete() {
						if (isEmpty) {
							try {
								action.run();
							} catch (Exception e) {
								onError(e);
								return;
							}
						}
						observer.onComplete();
					}
				};
			}
		};
	}
	
	// Since it is a single abstract method class, you can also express your ObservableOperator implementation as a lambda
	public static <T> ObservableOperator<T,T> doOnEmpty2(Action action) {
	    
		return observer -> new DisposableObserver<T>() {
	        boolean isEmpty = true;
	        
	        @Override
	        public void onNext(T value) {
	            isEmpty = false;
	            observer.onNext(value);
	        }
	        
	        @Override
	        public void onError(Throwable t) {
	            observer.onError(t);
	        }
	        
	        @Override
	        public void onComplete() {
	            if (isEmpty) {
	                try {
	                    action.run();
	                } catch (Exception e) {
	                    onError(e);
	                    return;
	                }
	            }
	            observer.onComplete();
	        }
	    };
	}
	
	public static void main(String[] args) {
		new ObservableOperatorImpl();

	}

}