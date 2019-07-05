package rx.flowable;

//-The core idea behind reactive streams: producer is not allowed to emit more events than requested by consumer.
//-RxJava 2.x brought several convenient operators that built on top of experience from previous versions.
// First of all RxJava 2.x does not allow you to implement Flowable (backpressure-aware) the same way as you can with Observable
// (requires extra BackpressureStrategy parameter).
//-It also fundamentally changes the way you implement your sources of events.
// Rather than pushing events whenever you feel like it you are only passively waiting for requests.

//-Downstream operators and subscribers are pulling data from your stream. This shift enables backpressure at all levels of your pipeline.

//-For most cases where a source is producing emissions faster than the downstream can process them, it is better
// to proactively make the source slow down in the first place and emit at a pace that agrees with the downstream operations.
//-This is known as backpressure or flow control, and it can be enabled by using a Flowable instead of an Observable.

//-The Flowable is a backpressured variant of the Observable that tells the source to emit at a pace specified by the downstream operations.
//-Flowable is just like an Observable with nearly all the operators we learned so far.
// You can convert from an Observable into a Flowable and vice-versa,

//-Pretty much all the Observable factories and operators also apply to Flowable. 

//-You can easily interoperate Observables and Flowables together. But you need to be careful and aware of
// the context they are being used in and where undesired bottlenecks can occur.

//-Why Flowable.range() starts with 128 emissions, and why observeOn() only sends 96 downstream before requesting another 96, leaving 32
// unprocessed emissions? The initial batch of emissions is a bit larger so some extra work is queued if there is any idle time.
// If (in theory) our Flowable operation started by requesting 96 emissions and continued to emit steadily at 96 emissions at a time,
// there would be moments where operations might wait idly for the next 96. Therefore, an extra rolling cache of 32 emissions is maintained
// to provide work during these idle moments, which can provide greater throughput.
//-This is much like a warehouse holding a little extra inventory to supply orders while it waits for more from the factory.

//-This is much like a warehouse holding a little extra inventory to supply orders while it waits for more from the factory.
//-What is great about Flowables and their operators is that they usually do all the work for you. You do not have to specify
// any backpressure policies or parameters unless you need to create your own Flowables from scratch or deal with sources.

//-The benefits offered from the Flowable are leaner usage of memory (preventing OutOfMemoryError exceptions) as well as prevention
// of MissingBackpressureException. The latter can occur if operations backpressure against a source but the source has no backpressure protocol
// in its implementation. However, the disadvantage of Flowable is that it adds overhead and may not perform as quickly as an Observable.

//	Use an Observable If...
// • You expect few emissions over the life of the Observable subscription (less than 1000) or the emissions are intermittent and far apart.
//   If you expect only a trickle of emissions coming from a source, an Observable will do the job just fine and have less overhead.
//   But when you are dealing with large amounts of data and performing complex operations on them, you will likely want to use a Flowable.
// • Your operation is strictly synchronous and has limited usage of concurrency. This includes simple usage of subscribeOn() at the start
//   of an Observable chain because the process is still operating on a single thread and emitting items synchronously downstream.
//   However, when you start zipping and combining different streams on different threads, parallelize, or use operators such as observeOn(),
//   interval(), and delay(), your application is no longer synchronous and you might be better-off using a Flowable.
// • You want to emit user interface events such as button clicks, ListView selections, or other user inputs on Android, JavaFX, or Swing.
//   Since users cannot programmatically be told to slow down, there is rarely any opportunity using a Flowable. To cope with rapid
//   user inputs, you are likely better-off using the operators discussed in Chapter 7, Switching, Throttling, Windowing, and Buffering.

//	Use a Flowable If...
// • You are dealing with over 10,000 elements and there is opportunity for the source to generate emissions in a regulated manner.
//   This is especially true when the source is asynchronous and pushes large amounts of data.
// • You want to emit from IO operations that support blocking while returning results, which is how many IO sources work.
//   Data sources that iterate records, such as lines from files or a ResultSet in JDBC, are especially easy to control because
//   iteration can pause and resume as needed. Network and Streaming APIs that can request a certain amount of returned results
//   can easily be backpressured as well.

//-Note in RxJava 1.0, the Observable was backpressured and was essentially what the Flowable is in RxJava 2.0.
//-The reason the Flowable and Observable became separate types is due to the merits of both for different situations,
// as described precedingly.

public class AnInfo {}
