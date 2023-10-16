package onboarding;

import java.util.List;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = List.of("answer");
        return answer;
    }

    static class emailValidation {

        private boolean checkDomain(String email) {
            int index = email.indexOf("@") + 1;
            return email.substring(index).equals("email.com");
        }

        private boolean length(String email) {
            return 11 <= email.length() && email.length() < 20;
        }
    }
}
