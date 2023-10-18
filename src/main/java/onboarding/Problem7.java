package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Problem7 {

	private Problem7() {}

	static Validation validation = new Validation();
	static UserMap userMap = new UserMap();
	static AllUser allUser = new AllUser();
	static Point point = new Point();
	static Output output = new Output();

	public static List<String> solution(String user, List<List<String>> friends,
		List<String> visitors) {
		if (validation.isValid(user, friends, visitors)) {
			Map<String, Set<String>> friendsList = userMap.generateUserMap(friends);
			Map<String, Integer> scoreBoard = allUser.getScore(friendsList, visitors);
			Map<String, Integer> friendsPoint = point.getFriendsPoint(user, scoreBoard, friendsList);
			Map<String, Integer> totalPoint = point.getVisitorsPoint(friendsPoint, visitors);
			return output.getOutput(totalPoint);
		}


		return Collections.emptyList();
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
			return !friends.isEmpty() && friends.size() <= 10_000;
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
			return visitors.size() <= 10_000;
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

		public Map<String, Integer> getScore(Map<String, Set<String>> userMap,
			List<String> visitors) {
			Map<String, Integer> score = new HashMap<>();
			Set<String> allUserSet = getUserSet(userMap, visitors);
			insertNameWithZero(score, allUserSet);
			return score;
		}

		private Set<String> getUserSet(Map<String, Set<String>> userMap, List<String> visitors) {
			Set<String> allUserSet = new HashSet<>();
			allUserSet.addAll(userMap.keySet());
			allUserSet.addAll(visitors);
			return allUserSet;
		}

		private void insertNameWithZero(Map<String, Integer> score, Set<String> allUserSet) {
			for (String string : allUserSet) {
				score.put(string, 0);
			}
		}
	}

	static class Point {

		public Map<String, Integer> getFriendsPoint(String user, Map<String, Integer> score,
			Map<String, Set<String>> userMap) {

			deleteFriends(user, score, userMap);
			addFriendPoint(user, score, userMap);
			return score;
		}

		public Map<String, Integer> getVisitorsPoint(Map<String, Integer> score,
			List<String> visitors) {
			for (String visitor : visitors) {
				score.computeIfPresent(visitor, (key, value) -> value + 1);
			}
			return score;
		}


		private void deleteFriends(String user, Map<String, Integer> score,
			Map<String, Set<String>> userMap
		) {
			Set<String> userFriends = userMap.get(user);
			Set<String> allUsers = score.keySet();
			for (String friend : userFriends) {
				if (allUsers.contains(friend)) {
					score.remove(friend);
				}
			}
			score.remove(user);
		}

		private void addFriendPoint(String user, Map<String, Integer> score,
			Map<String, Set<String>> userMap) {
			Set<String> userFriends = userMap.get(user);
			Set<String> allUsers = score.keySet();
			for (String person : allUsers) {
				if (userMap.containsKey(person)) {
					Set<String> personFriends = userMap.get(person);
					int pointsToAdd = calculatePointsToAdd(userFriends, personFriends);
					score.put(person, score.get(person) + pointsToAdd);
				}
			}
		}

		private int calculatePointsToAdd(Set<String> userFriends, Set<String> personFriends) {
			int pointsToAdd = 0;
			for (String friend : userFriends) {
				if (personFriends.contains(friend)) {
					pointsToAdd += 10;
				}
			}

			return pointsToAdd;
		}
	}

	static class Output {

		public List<String> getOutput(Map<String, Integer> score) {
			Map<String, Integer> sortedTreeMap = sort(score);
			return cutToMaxFivePeople(sortedTreeMap);
		}

		private Map<String, Integer> sort(Map<String, Integer> score) {
			List<String> keySet = new ArrayList<>(score.keySet());
			keySet.sort((o1, o2) -> {
				int valueComparison = score.get(o2).compareTo(score.get(o1));
				if (valueComparison != 0) {
					return valueComparison; // Value가 다른 경우 내림차순 정렬
				} else {
					return o1.compareTo(o2); // Value가 같으면 Key를 오름차순 정렬
				}
			});

			// 정렬된 키를 사용하여 새로운 Map을 만듭니다.
			Map<String, Integer> sortedMap = new LinkedHashMap<>();
			for (String key : keySet) {
				sortedMap.put(key, score.get(key));
			}

			return sortedMap;
		}


		private List<String> cutToMaxFivePeople(Map<String, Integer> sortedTreeMap) {
			List<String> result = new ArrayList<>();
			for (String person : sortedTreeMap.keySet()) {
				if (sortedTreeMap.get(person) != null) {
					result.add(person);
				}
			}
			return result;
		}
	}
}

