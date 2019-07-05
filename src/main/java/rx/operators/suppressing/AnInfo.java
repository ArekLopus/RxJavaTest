package rx.operators.suppressing;

//-There are a number of operators that will suppress emissions that fail to meet a specified criterion.
//-These operators work by simply not calling the onNext() function downstream for a disqualified emission, and therefore does not
// go down the chain to Observer.

public class AnInfo {}