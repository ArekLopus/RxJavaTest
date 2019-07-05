package rx.operators.combining;

import io.reactivex.Observable;

//-flatMap() dynamically merges Observables derived off each emission, there is a concatenation counterpart called concatMap().
//-You should prefer this operator if you care about ordering and want each Observable mapped from
// each emission to finish before starting the next one. 

//-concatMap() will merge each mapped Observable sequentially and fire it one at a time.
//-It will only move to the next Observable when the current one calls onComplete().
//-If source emissions produce Observables faster than concatMap() can emit from them, those Observables will be queued.

//-It is unlikely that you will ever want to use concatMap() to map to infinite Observables. This would result
// in subsequent Observables never firing. You will likely want to use flatMap() instead,

//-Returns a new Observable that emits items resulting from applying a function that you supply to each item emitted
// by the source ObservableSource, where that function returns an ObservableSource, and then emitting the items
// that result from concatenating those resulting ObservableSources. 
//-concatMap does not operate by default on a particular Scheduler.
public class ConcatMap {

	public ConcatMap() {
		
		Observable<String> source = Observable.just("One", "Two", "Three", "Four");
		source.concatMap(s -> Observable.fromArray(s.split("")))
		    .subscribe(System.out::println);
		

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ConcatMap();

	}

}
