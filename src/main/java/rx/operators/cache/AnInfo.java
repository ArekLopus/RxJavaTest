package rx.operators.cache;

//	Cache operators
//-When you want to cache all emissions indefinitely for the long term and do not need to control the subscription behavior to the source
// with ConnectableObservable, you can use the cache() operator.
//-It will subscribe to the source on the first downstream Observer that subscribes and hold all values indefinitely.
//-This makes it an unlikely candidate for infinite Observables or large amounts of data that could tax your memory.

//-Returns an Observable that subscribes to this ObservableSource lazily, caches all of its events and replays them,
// in the same order as received, to all the downstream subscribers.
//-cache does not operate by default on a particular Scheduler.

//-Do not use cache() unless you really want to hold all elements indefinitely and do not have plans to dispose it at any point.

public class AnInfo {}
