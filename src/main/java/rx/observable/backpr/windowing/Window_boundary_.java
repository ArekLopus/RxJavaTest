package rx.observable.backpr.windowing;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

//-The most powerful variance of window() is accepting another Observable as a boundary argument.
//-It does not matter what type this other Observable emits. All that matters is every time it emits something,
// it will use the timing of that emission as the window cut-off.
//-In other words, the arbitrary occurrence of emissions of another Observable will determine when to "slice" each window.

//-This is probably the most flexible way to window items based on highly variable events.
//-The boundary can be any Observable representing any kind of event happening at any time.
//-This idea of an Observable serving as a cut-off for another Observable is a very powerful pattern. 
public class Window_boundary_ {

	public Window_boundary_() throws InterruptedException {
		
		Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
		
		Observable.interval(300, TimeUnit.MILLISECONDS)
		    .map(i -> (i + 1) * 300)
		    .window(cutOffs)
		    .flatMapSingle(obs -> obs.reduce("", (total, next) -> total + (total.equals("") ? "" : "-") + next)) 
		    .subscribe(System.out::println);
		
		Thread.sleep(5000);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Window_boundary_();

	}

}



