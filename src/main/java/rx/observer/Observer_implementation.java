package rx.observer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Observer_implementation {

	public Observer_implementation() {
		
		Observable<String> source = Observable.just("One", "Two", "Three", "Four");
		
		Observer<Integer> myObserver = new Observer<Integer>() {
		    @Override
		    public void onSubscribe(Disposable d) {
		    }
		    @Override
		    public void onNext(Integer value) {
		        System.out.println("Received: " + value);
		    }
		    @Override
		    public void onError(Throwable e) {
		        e.printStackTrace();
		    }
		    @Override
		    public void onComplete() {
		        System.out.println("Done!");
		    }
		};
		
		source.map(String::length).filter(i -> i >= 4).subscribe(myObserver);
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Observer_implementation();

	}

}
