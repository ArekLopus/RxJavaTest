package rx.operators;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.observers.DisposableObserver;

//-To create your own ObservableOperator<Downstream,Upstream> (where Upstream is the upstream emission type
// and Downstream is the downstream emission type), you will need to implement its apply() method.
// This accepts an Observer<Downstream> observer argument and returns an Observer<Upstream>.
//-You can then use this ObservableOperator by calling it in the lift() operator in your Observable chain.
public class ObservableOperatorToList {

	public ObservableOperatorToList() {

		Observable.range(1, 5)
			.lift(myToList())
			.subscribe(v -> System.out.println("Operation 1: " + v));
		
		Observable.empty()
			.lift(myToList())
			.subscribe(v -> System.out.println("Operation 2: " + v));
		
		
		System.out.println("--- Main Thread Finished ---");

	}

	public static <T> ObservableOperator<List<T>, T> myToList() {
		
		return observer -> new DisposableObserver<T>() {
			List<T> list = new ArrayList<>();

			@Override
			public void onNext(T value) {
				list.add(value);			// add to List, but don't pass anything downstream
			}

			@Override
			public void onError(Throwable t) {
				observer.onError(t);
			}

			@Override
			public void onComplete() {
				observer.onNext(list); 		// push List downstream
				observer.onComplete();
			}
		};
	}
	
	
	public static void main(String[] args) {
		new ObservableOperatorToList();

	}

}