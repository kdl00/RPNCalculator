import java.util.*;

public class RPNCalculator {

	String _rpnInput = new String();
	Stack<String> _stack = new Stack<String>();
	double _answer = -1;

	public class RPNGenerator {

		private String _input;
		private StringBuffer _output;
		private Stack<Operator> _stack = new Stack<Operator>();

		public RPNGenerator(String input) {
			_input = input.trim();
			_output = new StringBuffer();

			// we have to replace spaces with nothing since the pattern does not
			// expect spaces in the string it is to match
			if (!ExpressionUtility.isExpressionValid(_input.replace(" ", ""))) {
				System.out.printf("The expression \"%s\" is invalid\n", _input);
				System.exit(-1);
			}

			generateRPNString();
			System.out.printf("RPN String: %s\n", getRPNString());
		}

		private void generateRPNString() {
			final String tokens[] = _input.split("(?=[+/*-])|(?<=[+/*-]+)");
			for (String token : tokens) {
				token = token.trim();
				if (!token.isEmpty()) {
					if (ExpressionUtility.isStringNumber(token)) {
						_output.append(token);
						_output.append(" ");
					} else if (ExpressionUtility.isStringOperator(token)) {
						// if higher or same as current operator precedence keep
						// popping the operators out and add them to output
						// string
						Operator op = new Operator(token);
						while (!_stack.isEmpty()
								&& (_stack.peek().getPrecedence() >= op
										.getPrecedence())) {
							_output.append(_stack.pop());
							_output.append(" ");
						}
						_stack.push(op);
					} else {
						System.out.printf("Illegal token found: \"%s\"\n",
								token);
						System.exit(-1);
					}
				}
			}
			while (!_stack.isEmpty()) {
				_output.append(_stack.pop());
				_output.append(" ");
			}
		}

		public String getRPNString() {
			return _output.toString();
		}

		public void printRPNString() {
			System.out.println(_output.toString());
		}

	}

	// rpnInput is a string that is the representation of an expression in
	// reverse polish notation
	public RPNCalculator(String input) {
		RPNGenerator gen = new RPNGenerator(input);
		_rpnInput = gen.getRPNString();
		calculate();
	}

	private void calculate() {
		String tokens[] = _rpnInput.split(" ");

		for (String token : tokens) {
			token = token.trim();
			if (!token.isEmpty()) {
				if (ExpressionUtility.isStringNumber(token)) {
					_stack.push(token);
				} else if (ExpressionUtility.isStringOperator(token)) {
					_stack.push(performOperation(_stack.pop(), _stack.pop(),
							token));
				} else {
					System.out.printf("Illegal token found: \"%s\"\n", token);
					System.exit(-1);
				}
			}
		}

		_answer = Double.parseDouble(_stack.pop());
	}

	private String performOperation(String int2, String int1, String operator) {
		double intA = Double.parseDouble(int1);
		double intB = Double.parseDouble(int2);
		double answer = -1;

		switch (operator) {
		case "+":
			answer = intA + intB;
			System.out.printf("%f + %f = %f\n", intA, intB, answer);
			break;
		case "-":
			answer = intA - intB;
			System.out.printf("%f - %f = %f\n", intA, intB, answer);
			break;
		case "*":
			answer = intA * intB;
			System.out.printf("%f * %f = %f\n", intA, intB, answer);
			break;
		case "/":
			answer = intA / intB;
			System.out.printf("%f / %f = %f\n", intA, intB, answer);
			break;
		default:
			System.out.printf("Unhandled operator \"%s\"!\n", operator);
		}

		return Double.toString(answer);
	}

	public String getAnswer() {
		return Double.toString(_answer);
	}

	public void printAnswer() {
		System.out.printf("Answer: %f\n", _answer);
	}
}
