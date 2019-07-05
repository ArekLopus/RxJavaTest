package rx.utils;

public class RxUtils {

	public static void consumeStringWithThreadPrint(String str) {
		System.out.println("Value: " + str + ", thread: " + Thread.currentThread().getName());
	}
	
	public static void consumeNumberWithThreadPrint(Number nr) {
		System.out.println("Value: " + nr + ", thread: " + Thread.currentThread().getName());
	}
	
	public static void consumeBooleanWithThreadPrint(Boolean bl) {
		System.out.println("Value: " + bl + ", thread: " + Thread.currentThread().getName());
	}
	
	public static void consumeThrowableWithThreadPrint(Throwable e) {
		System.out.println("Error: " + e.getMessage() + ", thread: " + Thread.currentThread().getName());
	}
	
	
	public static String getThreadNr() {
		return Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - 2);
	}
	
	public static String getThreadNr(int lastChars) {
		return Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - lastChars);
	}
	
		
	public static long hardWork(long millisToWait) {
		
		long start = System.currentTimeMillis();
		
		while(true) {
			if (System.currentTimeMillis() - start > millisToWait)
				break;
		}
		
		return millisToWait;
	}
}
