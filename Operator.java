public class Operator {
	private String _value;
	private int _precedence;

	public Operator(String operator) {
		_value = operator;
		_precedence = determinePrecedence(operator);
	}

	public String getOperator() {
		return _value;
	}

	public int getPrecedence() {
		return _precedence;
	}

	// We must override the toString method in order for "_stack.push(op);"
	// to return the string value (the actual operator) otherwise garbage
	// is printed 
	public String toString() {
		return _value;
	}

	private int determinePrecedence(String operator) {
		int precedence = -1;

		switch (operator) {
		case "+":
		case "-":
			precedence = 1;
			break;
		case "*":
		case "/":
			precedence = 2;
			break;
		default:
			System.out.printf("Unhandled operator \"%s\"!", operator);
		}

		return precedence;
	}
}
