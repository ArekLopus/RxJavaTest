package rx.observable.create;

import io.reactivex.Observable;

//-Create a disposable resource that has the same lifespan as the Observable
//-The using operator is a way you can instruct an Observable to create a resource that exists only during the lifespan
// of the Observable and is disposed of when the Observable terminates.
public class Observable_using {

	public Observable_using() {

		Observable<Character> values = Observable.using(
			() -> "resource",
			r -> {
				return Observable.create(o -> {
					for (Character c : r.toCharArray()) {
						o.onNext(c);
					}
					o.onComplete();
				});
			},
			r -> System.out.println("Disposed: " + r)
		);
		
		values.subscribe(e -> System.out.println("Observer1: " + e));
		

		System.out.println("--- Main Thread Finished ---");

	}

	public static void main(String[] args) {
		new Observable_using();

	}

}
