package rx.operators.transforming;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.reactivex.Observable;

//-Returns an Observable that applies a specified function to each item emitted by the source ObservableSource
// and emits the results of these function applications. 
//-map does not operate by default on a particular Scheduler.
public class Map {

	public Map() {
		
		Observable.just("1918-11-11", "1941-12-07", "1945-05-07")
			.map(LocalDate::parse)
			.subscribe(s -> System.out.println(s.format(DateTimeFormatter.ofPattern("dd_MM_yy")) + ", thread: " + Thread.currentThread().getName()));
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new Map();

	}

}
