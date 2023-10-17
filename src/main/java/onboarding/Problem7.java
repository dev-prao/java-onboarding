package onboarding;

import java.util.Collections;
import java.util.List;

public class Problem7 {
	public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
		List<String> answer = Collections.emptyList();
		return answer;
	}

	static class Validation {

		public boolean isValid(String user, List<List<String>> friends, List<String> visitors) {
			return isUserLengthValid(user) && isUserLowerCase(user)
				&& isFriendsSizeValid(friends) && isFriendSizeValid(friends)
				&& isVisitorsSizeValid(visitors);
		}

		private boolean isUserLengthValid(String user) {
			return 1 <= user.length() && user.length() <= 30;
		}

		private boolean isUserLowerCase(String user) {
			return user.equals(user.toLowerCase());
		}

		private boolean isFriendsSizeValid(List<List<String>> friends) {
			return 1 <= friends.size() && friends.size() <= 10_000;
		}

		private boolean isFriendSizeValid(List<List<String>> friends) {
			for (List<String> friend : friends) {
				if (friend.size() != 2) {
					return false;
				}
			}
			return true;
		}

		private boolean isVisitorsSizeValid(List<String> visitors) {
			return 0 <= visitors.size() && visitors.size() <= 10_000;
		}

	}
}
