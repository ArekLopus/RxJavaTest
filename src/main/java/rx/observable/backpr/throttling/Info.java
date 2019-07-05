package rx.observable.backpr.throttling;

//-The buffer() and window() operators batch up emissions into collections or Observables
// based on a defined scope, which regularly consolidates rather than omits emissions.
//-The throttle() operator, however, omits emissions when they occur rapidly.
//-This is helpful when rapid emissions are assumed to be redundant or unwanted, such as a user clicking on a button repeatedly.
//-For these situations, you can use the throttleLast(), throttleFirst(), and throttleWithTimeout() operators
// to only let the first or last element in a rapid sequence of emissions through.
//-How you choose one of the many rapid emissions is determined by your choice of operator, parameters, and arguments.
public class Info {}
