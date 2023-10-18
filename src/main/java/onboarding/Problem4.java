package onboarding;

class Problem4 {

	private Problem4() {}

	static Validation validation = new Validation();
	static Converter converter = new Converter();

	public static String solution(String word) {
		if (validation.length(word)) {
			return converter.convert(word);
		}
		return "";
	}

	static class Validation {

		public boolean length(String word) {
			return !word.isEmpty() && word.length() <= 1000;
		}
	}

	static class Converter {

		public String convert(String word) {
			char[] words = word.toCharArray();
			for (int i = 0; i < words.length; i++) {
				if (65 <= words[i] && words[i] <= 90) {
					words[i] = (char)(155 - words[i]);
				}
				if (97 <= words[i] && words[i] <= 122) {
					words[i] = (char)(219 - words[i]);
				}
			}
			return new String(words);
		}
	}
}
