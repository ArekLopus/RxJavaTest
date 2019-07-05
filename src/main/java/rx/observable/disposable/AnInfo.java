package rx.observable.disposable;

//-Disposable is passed in the implementation of an Observer through the onSubscribe().
//-This method was added in RxJava 2.0, and it allows the Observer to have the ability to dispose of the subscription at any time.
//-The Disposable is sent from the source all the way up the chain to the Observer, so each step in the Observable chain has access to it.
//-Note that passing an Observer impl to the subscribe() will be void and not return a Disposable since it is assumed that the Observer will handle it.
//-You can extend ResourceObserver which uses a default Disposable handling. Pass this to subscribeWith() instead of subscribe(),
// and you will get the default Disposable returned.

//-If your Observable is a long-running or infinite Observable, you should ideally check the isDisposed() of ObservableEmitter regularly,
// to see whether you should keep sending emissions. This prevents unnecessary work from being done if the subscription is no longer active.
//-Observable MUST run on different thread otherwise it WONT work!


//	CompositeDisposable
//-If you have several subscriptions that need to be managed and disposed of, it can be helpful to use CompositeDisposable.
//-It implements Disposable, but it internally holds a collection of disposables, which you can add to and then dispose all at once.
//-CompositeDisposable is a simple but helpful utility to maintain a collection of disposables that you can add to
// by calling add() or addAll(). When you no longer want these subscriptions, you can call dispose() to dispose of all of them at once.
public class AnInfo {}
