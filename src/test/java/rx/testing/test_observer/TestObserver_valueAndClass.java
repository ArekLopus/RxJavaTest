package rx.testing.test_observer;

import io.reactivex.observers.TestObserver;

//-Appends the class name to a non-null value.
public class TestObserver_valueAndClass {
	
	
	public TestObserver_valueAndClass() {
		
		System.out.println(TestObserver.valueAndClass("Abc"));
		System.out.println(TestObserver.valueAndClass(1));
		System.out.println(TestObserver.valueAndClass(1L));
		System.out.println(TestObserver.valueAndClass(1F));
		System.out.println(TestObserver.valueAndClass(1D));
		
	}
	
	public static void main(String[] args) {
		new TestObserver_valueAndClass();

	}
	
}
