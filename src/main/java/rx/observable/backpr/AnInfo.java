package rx.observable.backpr;

//-It is not uncommon to run into situations where an Observable is producing emissions faster than an Observer can consume them.
//-This happens particularly when you introduce concurrency, and the Observable chain has different operators running on different Schedulers.
//-Whether it is one operator struggling to keep up with a preceding one, or the final Observer struggling to keep up with emissions
// from the upstream, bottlenecks can occur where emissions start to queue up behind slow operations.

//-Of course, the ideal way to handle bottlenecks is to leverage backpressure using Flowable instead of Observable.
//-The Flowable is not much different than the Observable other than that it tells the source to slow down by having the Observer request
// emissions at its own pace.

//-But not every source of emissions can be backpressured. You cannot instruct Observable.interval() (or even Flowable.interval())
// to slow down because the emissions are logically time sensitive. Asking it to slow down would make those time-based emissions inaccurate.
// User input events, such as button clicks, logically cannot be backpressured either because you cannot programmatically control the user.

//-Thankfully, there are some operators that help cope with rapidly firing sources without using backpressure and are especially
// appropriate for situations where backpressure cannot be utilized. Some of these operators batch up emissions into chunks that are more easily
// consumed downstream. Others simply sample emissions while ignoring the rest. There is even a powerful switchMap() operator that functions
// similarly to flatMap() but will only subscribe to the Observable derived from the latest emission and dispose of any previous ones.

public class AnInfo {}
