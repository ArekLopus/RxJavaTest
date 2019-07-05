package rx.observable.backpr.switching;

//-There is a powerful operator called switchMap(). Its usage feels like flatMap(), but it has one important behavioral difference:
// it will emit from the latest Observable derived from the latest emission and dispose of any previous Observables that were processing.
//-It allows you to cancel an emitting Observable and switch to a new one, preventing stale or redundant processing.

//-switchMap() is like flatMap() except that it will cancel any previous Observables that were processing and only chase after the latest one.
//-This can be helpful in many situations to prevent redundant or stale work and is especially effective in user interfaces where rapid user
// inputs create stale requests. You can use it to cancel DB queries, web requests, and other expensive tasks and replace it with a new task.

//-Say we want to run a process every 2 seconds, but we want to cancel (or more technically, dispose()) previous instances of the process
// and only run the latest one. This is easy to do with switchMap().

//-For switchMap() to work effectively, the thread pushing emissions into switchMap() cannot be occupied doing the work inside switchMap().
//-This means that you may have to use observeOn() or subscribeOn() inside switchMap() to do work on a different thread.
//-If the operations inside switchMap() are expensive to stop (fe, a DB query using RxJava-JDBC), you might want to use
// unsubscribeOn() as well to keep the triggering thread from becoming occupied with disposal.
//-A neat trick you can do to cancel work within switchMap() (without providing new work immediately) is to conditionally yield
// Observable.empty(). This can be helpful to cancel a long-running or infinite process.
public class AnInfo {}