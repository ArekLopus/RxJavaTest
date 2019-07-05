package rx.flowable.generate;

//	Flowable.generate()

//-The core idea behind reactive streams: producer is not allowed to emit more events than requested by consumer.
//-RxJava 2.x brought several convenient operators that built on top of experience from previous versions.
// First of all RxJava 2.x does not allow you to implement Flowable (backpressure-aware) the same way as you can with Observable
// (requires extra BackpressureStrategy parameter).

//-The difference between create() and generate() lies in responsibility.
//-Flowable.create() is suppose to generate the stream in its entirety with no respect to backpressure.
// It simply produces events whenever it wishes to do so.
//-Flowable.generate() on the other hand is only allowed to generate one event at a time (or complete a stream).
// Backpressure mechanism transparently figures out how many events it needs at the moment.
// generate() is called appropriate number of times, for example 128 times in case of observeOn().

//-Flowable.generate() is: a holder for (im)mutable state and a function that generates next event based on it.
//	Flowable<Long> naturalNumbers = Flowable.generate(() -> 0L, (state, emitter) -> {
//  	emitter.onNext(state);
//    	return state + 1;
//	});
//-The first argument to generate() is an initial state (factory), 0L in our case.
//-Now every time a subscriber or any downstream operator asks for some number of events, the lambda expression is invoked.
// Its responsibility is to call onNext() at most once (emit at most one event) somehow based on supplied state. 
// When lambda is invoked for the first time the state is equal to initial value 0L. However we are allowed to modify the state
// and return its new value. In this example we increment long so that subsequent invocation of lambda expression receives state = 1L.
//-Such a programming model is obviously harder than a while loop.
//-It also fundamentally changes the way you implement your sources of events.
// Rather than pushing events whenever you feel like it you are only passively waiting for requests.

//-Downstream operators and subscribers are pulling data from your stream. This shift enables backpressure at all levels of your pipeline.


//-If you are creating your own custom sources, Flowable.create() or the onBackPressureXXX() operators are somewhat compromised in how they
// handle backpressure requests. While quick and effective for some cases, caching emissions or simply dropping them is not always desirable.
// It would be better to make the source backpressured in the first place.
//-Thankfully, Flowable.generate() exists to help create backpressure, respecting sources at a nicely abstracted level.
// It will accept a Consumer<Emitter<T>> much like Flowable.create(), but it will use a lambda to specify what onNext(), onComplete(), onError()
// events to pass each time an item is requested from the upstream.
//-Before you use Flowable.generate(), consider making your source Iterable<T> instead and passing it to Flowable.fromIterable().
//-The Flowable.fromIterable() will respect backpressure and might be easier to use for many cases. 
//-Otherwise, Flowable.generate() is your next best option if you need something more specific.

public class AnInfo {}
