package demo;

public sealed interface Expression permits IncrementExpr, NegateExpr, AddExpr, MultiplyExpr {
	public int evaluate();
}

record IncrementExpr(int n) implements Expression {

	public int evaluate() {
		return n + 1;
	}
	
}
record NegateExpr(Expression n) implements Expression {
	
	public int evaluate() {
		return n.evaluate() * -1;
	}

}
record AddExpr(Expression left, Expression right) implements Expression {
	
	public int evaluate() {
		return left.evaluate() + right.evaluate();
	}

}
record MultiplyExpr(Expression left, Expression right) implements Expression {
	
	public int evaluate() {
		return left.evaluate() * left.evaluate();
	}

}

