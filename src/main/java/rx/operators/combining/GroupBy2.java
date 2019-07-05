package rx.operators.combining;

import java.util.Collection;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupBy2 {

	public GroupBy2() {
		
		Observable<String> source = Observable.just("One", "Two" , "Three", "Four", "Five");
		Observable<GroupedObservable<Integer,String>> byLengths = source.groupBy(s -> s.length());
		
		Observable<Map<Integer, Collection<String>>> flatMapSingle = 
				byLengths.flatMapSingle(grp -> grp.toMultimap(i -> grp.getKey()));
		
		flatMapSingle.subscribe(System.out::println);
			

		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new GroupBy2();

	}

}
