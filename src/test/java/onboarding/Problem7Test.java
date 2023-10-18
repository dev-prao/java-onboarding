package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem7Test {

	@Test
	public void 유저맵에_데이터가_들어가는지_확인() throws Exception {
		//given
		List<List<String>> friends = new ArrayList<>(Arrays.asList(
			Arrays.asList("donut", "andole"),
			Arrays.asList("donut", "jun"),
			Arrays.asList("donut", "mrko"),
			Arrays.asList("shakevan", "andole"),
			Arrays.asList("shakevan", "jun"),
			Arrays.asList("shakevan", "mrko")
		));

		//when
		Problem7.UserMap userMap = new Problem7.UserMap();
		Map<String, Set<String>> result = userMap.generateUserMap(friends);

		//then
		Set<String> expected = new HashSet<>(Arrays.asList("andole", "jun", "mrko"));
		Set<String> actual = result.get("donut");
		System.out.println(actual);
		assertTrue(expected.size() == actual.size() && expected.containsAll(actual));

	}

	@Test
	public void AllUser_생성_확인() throws Exception {
		//given
		List<List<String>> friends = new ArrayList<>(Arrays.asList(
			Arrays.asList("donut", "andole"),
			Arrays.asList("donut", "jun"),
			Arrays.asList("donut", "mrko"),
			Arrays.asList("shakevan", "andole"),
			Arrays.asList("shakevan", "jun"),
			Arrays.asList("shakevan", "mrko")
		));
		List<String> visitors = new ArrayList<>(
			Arrays.asList("bedi", "bedi", "donut", "bedi", "shakevan"));
		Problem7.UserMap userMap = new Problem7.UserMap();
		Map<String, Set<String>> result = userMap.generateUserMap(friends);

		//when
		Problem7.AllUser allUser = new Problem7.AllUser();
		Map<String, Integer> allUserList = allUser.getScore(result, visitors);

		//then
		System.out.println(allUserList);
	}


	@Test
	public void 친구삭제() throws Exception {
		Problem7.UserMap userMap = new Problem7.UserMap();
		Problem7.AllUser allUser = new Problem7.AllUser();
		Problem7.Point point = new Problem7.Point();
		//given
		String user = "mrko";
		List<List<String>> friends = new ArrayList<>(Arrays.asList(
			Arrays.asList("donut", "andole"),
			Arrays.asList("donut", "jun"),
			Arrays.asList("donut", "mrko"),
			Arrays.asList("shakevan", "andole"),
			Arrays.asList("shakevan", "jun"),
			Arrays.asList("shakevan", "mrko")
		));
		List<String> visitors = new ArrayList<>(
			Arrays.asList("bedi", "bedi", "donut", "bedi", "shakevan"));



		//when
		Map<String, Set<String>> result = userMap.generateUserMap(friends);
		Map<String, Integer> score = allUser.getScore(result, visitors);
		Set<String> userFriends = result.get(user);
		Set<String> allUsers = score.keySet();
		score = point.getFriendsPoint(user, score, result);

		//then
		System.out.println(score);
	}

	@Test
	public void 점수추가() throws Exception {
		Problem7.UserMap userMap = new Problem7.UserMap();
		Problem7.AllUser allUser = new Problem7.AllUser();
		Problem7.Point point = new Problem7.Point();
		//given
		String user = "mrko";
		List<List<String>> friends = new ArrayList<>(Arrays.asList(
			Arrays.asList("donut", "andole"),
			Arrays.asList("donut", "jun"),
			Arrays.asList("donut", "mrko"),
			Arrays.asList("shakevan", "andole"),
			Arrays.asList("shakevan", "jun"),
			Arrays.asList("shakevan", "mrko")
		));
		List<String> visitors = new ArrayList<>(
			Arrays.asList("bedi", "bedi", "donut", "bedi", "shakevan"));



		//when
		Map<String, Set<String>> result = userMap.generateUserMap(friends);
		Map<String, Integer> score = allUser.getScore(result, visitors);
		Set<String> userFriends = result.get(user);
		Set<String> allUsers = score.keySet();
		score = point.getFriendsPoint(user, score, result);
		score = point.getVisitorsPoint(score, visitors);

		//then
		System.out.println(score);
	}

	@Test
	public void 정렬및출력() throws Exception {
		Problem7.UserMap userMap = new Problem7.UserMap();
		Problem7.AllUser allUser = new Problem7.AllUser();
		Problem7.Point point = new Problem7.Point();
		Problem7.Output output = new Problem7.Output();

		//given
		String user = "mrko";
		List<List<String>> friends = new ArrayList<>(Arrays.asList(
			Arrays.asList("donut", "andole"),
			Arrays.asList("donut", "jun"),
			Arrays.asList("donut", "mrko"),
			Arrays.asList("shakevan", "andole"),
			Arrays.asList("shakevan", "jun"),
			Arrays.asList("shakevan", "mrko")
		));
		List<String> visitors = new ArrayList<>(
			Arrays.asList("bedi", "bedi", "donut", "bedi", "shakevan"));
		Map<String, Set<String>> result = userMap.generateUserMap(friends);
		Map<String, Integer> score = allUser.getScore(result, visitors);
		Set<String> userFriends = result.get(user);
		Set<String> allUsers = score.keySet();
		score = point.getFriendsPoint(user, score, result);
		score = point.getVisitorsPoint(score, visitors);

		//when
		List<String> answer = output.getOutput(score);

		//then
		System.out.println(answer);
	}
}
