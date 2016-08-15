package com.xtfggef.dp.visit;

public class MyVisitor implements Visitor {

	@Override
	public void visit(Subject sub) {
		System.out.println("visit the subject£º"+sub.getSubject());
	}
}
