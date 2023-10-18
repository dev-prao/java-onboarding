package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Problem5 {

	private Problem5() {
	}

	static Validation validation = new Validation();
	static CurrencyExchange currencyExchange = new CurrencyExchange();

	public static List<Integer> solution(int money) {
		if (validation.range(money)) {
			return currencyExchange.exchange(money);
		}
		return Collections.emptyList();
	}

	static class Validation {

		public boolean range(int money) {
			return 1 <= money && money <= 1_000_000;
		}
	}

	static class CurrencyExchange {

		public List<Integer> exchange(int money) {
			List<Integer> currency = new ArrayList<>();
			int[] currencies = new int[] {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
			int change = money;
			int index = 0;
			while (index <= 8) {
				int count = change / currencies[index];
				currency.add(count);
				change %= currencies[index];
				index++;
			}
			return currency;
		}
	}
}
