/*
 * This class consists of several static methods including:
 *  - Determining if an expression is valid (according to infix notation)
 *  - Determining if a string is a number
 *  - Determining if a string is an operator
 */
public class ExpressionUtility {

	public static Boolean isExpressionValid(String exp) {
		final String expValidationPat = "^\\d+(\\.\\d+)?([\\+\\-/\\*]\\d+(\\.\\d+)?)+$";
		if (exp.matches(expValidationPat)) {
			return true;
		}

		return false;
	}

	public static Boolean isStringNumber(String str) {
		final String numPattern = "^\\d+(\\.\\d+)?$";
		if (str.matches(numPattern)) {
			return true;
		}
		return false;
	}

	public static Boolean isStringOperator(String str) {
		final String oprPattern = "^(\\+|\\-|\\*|/)$";
		if (str.matches(oprPattern)) {
			return true;
		}
		return false;
	}
}
