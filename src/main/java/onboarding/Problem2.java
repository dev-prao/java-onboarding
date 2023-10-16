package onboarding;

import java.util.Stack;
import java.util.stream.Collectors;

class Problem2 {
	private Problem2() {}

	static Validation validation = new Validation();
	static Decode decode = new Decode();

	public static String solution(String cryptogram) {
		if (validation.validate(cryptogram)) {
			return decode.getDecode(cryptogram);
		}
		return "";
	}

	static class Validation {

		public boolean validate(String cryptogram) {
			return length(cryptogram) && isLowerCase(cryptogram);
		}

		private boolean length(String cryptogram) {
			return !cryptogram.isEmpty() && cryptogram.length() <= 1000;
		}

		private boolean isLowerCase(String cryptogram) {
			return cryptogram.equals(cryptogram.toLowerCase());
		}
	}

	static class Decode {

		public String getDecode(String cryptogram) {
			Stack<Character> stack = new Stack<>();

			int index = 0;
			while (index < cryptogram.length()) {
				if (stack.isEmpty() || !stack.peek().equals(cryptogram.charAt(index))) {
					stack.push(cryptogram.charAt(index));
					++index;
					continue;
				}
				while (index < cryptogram.length() && stack.peek().equals(cryptogram.charAt(index))) {
					++index;
				}
				stack.pop();
			}
			return stack.stream().map(Object::toString).collect(Collectors.joining());
		}
	}
}
