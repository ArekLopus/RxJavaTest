package rx.observable.backpr.windowing;

import io.reactivex.Observable;

//-You can also provide a skip argument. This is how many emissions need to be skipped before starting a new window.

//-If skip is equal to count, the skip has no effect. But if they are different, you can get some interesting behaviors.
//-This will essentially cause every third element to not be buffered.

//-If you make skip less than count, you can get some interesting rolling buffers.
// If you buffer items into a size of 3 but have skip of 1, you will get rolling windows.
//-Fe, we emit the numbers 1 through 5 but create windows 1-2-3, then 2-3-4, then 3-4-5], and so on.
public class Window_size_skipElement {

	public Window_size_skipElement() {
		
		Observable.range(1,10)
	    	.window(2, 3)			// skip every 3rd element (no 3, 6, 9)
	    	.flatMapSingle(obs -> obs.reduce("", (total, next) -> total + (total.equals("") ? "" : "-") + next))
	    	.subscribe(System.out::println);
		System.out.println("");
		
		
		Observable.range(1,5)
	    	.window(3, 1)
	    	.flatMapSingle(obs -> obs.reduce("", (total, next) -> total + (total.equals("") ? "" : "-") + next))
	    	.subscribe(System.out::println);
		
		
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	
	public static void main(String[] args) {
		new Window_size_skipElement();

	}

}



