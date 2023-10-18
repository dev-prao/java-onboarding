package onboarding;

class Problem3 {

    private Problem3() {}

    static Count count = new Count();

    public static int solution(int number) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            answer += count.getCount(i);
        }
        return answer;
    }

    static class Count {

        public int getCount(int number) {
            int count = 0;
            while (number != 0) {
                if (hasThreeSixNine(number % 10)) {
                    count++;
                }
                number /= 10;
            }
            return count;
        }

        private boolean hasThreeSixNine(int number) {
			return number == 3 || number == 6 || number == 9;
		}
    }
}
