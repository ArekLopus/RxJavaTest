package rx.flowable.create;

//-Pretty much all the Observable factories and operators you learned up to this point also apply to Flowable. 
//-On the factory side, there is Flowable.range(), Flowable.just(), Flowable.fromIterable(), and Flowable.interval().
// Most of these implement backpressure for you, and usage is generally the same as the Observable equivalent. 

//-However, consider Flowable.interval(), which pushes time-based emissions at fixed time intervals.
// Can this be backpressured logically? Contemplate the fact that each emission is sensitively tied to the time it emits.
// If we slowed down Flowable.interval(), our emissions would no longer reflect time intervals and become misleading.
//-Therefore, Flowable.interval() is one of those few cases in the standard API that can throw MissingBackpressureException
// the moment downstream requests backpressure.
//-To overcome this issue, you can use operators such as onBackpresureDrop() or onBackPressureBuffer().

//-Flowable.interval() is one of those factories that logically cannot be backpressured at the source, so you can use operators after
// it to handle backpressure for you. Otherwise, most of the other Flowable factories you work with support backpressure.

//-Create a Flowable feels much like Observable.create(), but there is one critical difference;
// you must specify a BackpressureStrategy as a second argument.
//-This enumerable type does not by any means provide magic implementations of backpressure support.
// This simply supports backpressure by caching or dropping emissions or not implementing backpressure at all.
//	BackpressureStrategy	Description
//	MISSING					-Essentially results in no backpressure implementation at all. The downstream must deal with backpressure overflow,
//							 which can be helpful when used with onBackpressureXXX() operators, which we will cover later in this chapter.
//	ERROR					-Signals a MissingBackpressureException the moment the downstream cannot keep up with the source.
//	BUFFER					-Queues up emissions in an unbounded queue until the downstream is able to consume them,
//							 but can cause an OutOfMemoryError if the queue gets too large.
//	DROP					-If the downstream cannot keep up, this will ignore upstream emissions and not queue anything while the downstream is busy.
//	LATEST					-This will keep only the latest emission until the downstream is ready to receive it.

//-There is another way that you can implement BackpressureStrategy against a source that has no notion of backpressure.
//-You can turn an Observable into Flowable easily by calling its toFlowable() operator, which accepts a BackpressureStrategy as an arg.
//-Note that toFlowable(), with a buffering strategy, is going to have an unbounded queue, which can cause an OutOfMemoryError.

//-If you are provided a Flowable that has no backpressure implementation (including ones derived from Observable),
// you can apply BackpressureStrategy using onBackpressureXXX() operators.
//-These also provide a few additional configuration options. This can be helpful if, fe, you have a Flowable.interval() that emits faster
// than consumers can keep up. Flowable.interval() cannot be slowed down at the source because it is time-driven,
// but we can use an onBackpressureXXX() operator to proxy between it and the downstream.
//-Sometimes, Flowable may simply be configured with BackpressureStrategy.MISSING
// so these onBackpressureXXX() operators can specify the strategy later.
public class AnInfo {}
