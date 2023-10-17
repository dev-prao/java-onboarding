package onboarding;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem7 {
	public static List<String> solution(String user, List<List<String>> friends,
		List<String> visitors) {
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

	static class UserMap {

		public Map<String, Set<String>> generateUserMap(List<List<String>> friends) {
			Map<String, Set<String>> userMap = new HashMap<>();
			for (List<String> friend : friends) {
				addFriends(userMap, friend);
			}
			return userMap;
		}

		private void addFriends(Map<String, Set<String>> userMap, List<String> friend) {
			String first = friend.get(0);
			String second = friend.get(1);
			userMap.computeIfAbsent(first, s -> new HashSet<>()).add(second);
			userMap.computeIfAbsent(second, s -> new HashSet<>()).add(first);
		}
	}

	static class AllUser {

		public HashMap<String, Integer> getAllUser(Map<String, Set<String>> userMap, List<String> visitors) {
			HashMap<String, Integer> score = new HashMap<>();
			Set<String> allUserSet = new HashSet<>();
			allUserSet.addAll(userMap.keySet());
			allUserSet.addAll(visitors);
			for (String string : allUserSet) {
				score.put(string, 0);
			}
			return score;
		}
	}
}

