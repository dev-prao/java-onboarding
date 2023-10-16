package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        String answer = "answer";
        return answer;
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
}
