package rx.operators.collection;

//	Collection Operators
//-Collection operators will accumulate all emissions into a collection such as a list or map and then emit that collection as a single emission.
//-Collection operators are another form of reducing operators since they consolidate emissions into a single one.
// We will cover them separately since they are a significant category on their own, though.
//-Note that you should avoid reducing emissions into collections for the sake of it. It can undermine the benefits of reactive programming
// where items are processed in a beginning-to-end, one-at-a-time sequence. You only want to consolidate emissions into collections
// when you are logically grouping them in some way.

public class AnInfo {}
