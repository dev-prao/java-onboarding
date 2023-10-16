package onboarding;

import java.util.Collections;
import java.util.List;

public class Problem5 {
    public static List<Integer> solution(int money) {
        List<Integer> answer = Collections.emptyList();
        return answer;
    }

    static class Validation {

        public boolean range(int money) {
            return 1 <= money && money <= 1_000_000;
        }
    }
}
