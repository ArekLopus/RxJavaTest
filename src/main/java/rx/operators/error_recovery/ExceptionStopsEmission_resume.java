package rx.operators.error_recovery;

import io.reactivex.Observable;

//-onError() event is passed down the Observable chain to the Observer.
//-After that, the subscription terminates and no more emissions will occur.
public class ExceptionStopsEmission_resume {
	
	public ExceptionStopsEmission_resume() {
		
		//-If you want to resume emissions, you will just want to handle the error within the map() operator where the error can occur.
		//-You do this instaed of onErrorReturn() or onErrorReturnItem():
		Observable.just(5, 2, 4, 0, 3, 2, 8)
			.map(i -> {
				try {
					return 10 / i;
				} catch (ArithmeticException e) {
					return -1;
				}
			})
			.subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("ERROR: " + e));	
		
		System.out.println("--- Main Thread Finished ---");
		
	}

	public static void main(String[] args) {
		new ExceptionStopsEmission_resume();

	}

}
