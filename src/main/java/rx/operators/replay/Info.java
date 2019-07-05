package rx.operators.replay;

//-Replaying and caching data is a multicasting activity.
//-Multicasting also allows us to cache values that are shared across multiple Observers. This may sound surprising, but when you think
// about it long enough, you may realize this makes sense. If we are sharing data across multiple Observers, it makes sense that any
// caching feature would be shared across Observers too. 

//-The replay() operator is a powerful way to hold onto previous emissions within a certain scope and re-emit them when a new Observer comes in.
// It will return a ConnectableObservable that will both multicast emissions as well as emit previous emissions defined in a scope. Previous
// emissions it caches will fire immediately to a new Observer so it is caught up, and then it will fire current emissions from that point forward.
public class Info {}