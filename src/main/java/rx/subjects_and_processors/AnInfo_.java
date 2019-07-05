package rx.subjects_and_processors;

//-Subjects are both an Observer and an Observable, acting as a proxy mulitcasting device (kind of like an event bus).
//-They do have their place in reactive programming, but you should exhaust your other options before utilizing them.
//-Erik Meijer, the creator of ReactiveX, described them as the "mutable variables of reactive programming".
// Just like mutable variables are necessary at times even though you should strive for immutability,
// Subjects are sometimes a necessary tool to reconcile imperative paradigms with reactive ones.

//-Subject is an abstract type that implements both Observable and Observer.
//-This means that you can manually call onNext(), onComplete(), and onError() on a Subject,
// and it will, in turn, pass those events downstream toward its Observers.

//-The simplest Subject type is the PublishSubject, which, like all Subjects, hotly broadcasts to its downstream Observers.
//-Other Subject types add more behaviors, but PublishSubject is the "vanilla" type, if you will.

//-Subjects act like magical devices that can bridge imperative programming with reactive programming

//	Subject<String> subject = PublishSubject.create();
//	subject.map(String::length)
//	    .subscribe(System.out::println);
//	subject.onNext("One");
//	subject.onNext("Two");
//	subject.onNext("Three");
//	subject.onComplete();

//-The first Observable to call onComplete() on Subject is going to cease other Observables from pushing their emissions,
// and downstream cancellation requests are ignored. This means that you will most likely use Subjects for infinite, event-driven
// (that is, user action-driven) Observables. That being said, we will next look at cases where Subjects become prone to abuse.

//	When to use Subjects
//-More likely, you will use Subjects to eagerly subscribe to an unknown number of multiple source Observables and consolidate
// their emissions as a single Observable. Since Subjects are an Observer, you can pass them to a subscribe() method easily.
public class AnInfo_ {}
