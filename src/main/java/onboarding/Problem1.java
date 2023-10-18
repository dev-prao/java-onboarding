package onboarding;

import java.util.List;

class Problem1 {

	private Problem1() {}

	static Validation validation = new Validation();
	static Calculation calculation = new Calculation();
	static Output output = new Output();

	public static int solution(List<Integer> pobi, List<Integer> crong) {
		if (validation.validate(pobi) && validation.validate(crong)) {
			int pobiScore = calculation.getMaxScore(pobi);
			int crongScore = calculation.getMaxScore(crong);
			return output.getOutput(pobiScore, crongScore);
		}
		return Output.EXCEPTION;
	}

	static class Validation {
		private static final int FIRST_PAGE = 1;
		private static final int LAST_PAGE = 400;

		public boolean validate(List<Integer> pages) {
			return isValidPage(pages) && isOdd(pages.get(0)) && isEven(pages.get(1)) && cotainsPageNumber(pages)
				&& isContinuous(pages) && isNotFirstPageOrLastPage(pages) && isSizeTwo(pages);
		}

		private boolean isValidPage(List<Integer> pages) {
			int leftPage = pages.get(0);
			int rightPage = pages.get(1);
			return FIRST_PAGE <= leftPage && leftPage <= LAST_PAGE
				&& FIRST_PAGE <= rightPage && rightPage <= LAST_PAGE;
		}

		private boolean isOdd(int page) {
			return page % 2 == 1;
		}

		private boolean isEven(int page) {
			return page % 2 == 0;
		}

		private boolean cotainsPageNumber(List<Integer> pages) {
			return pages.get(0) != null && pages.get(1) != null;
		}

		private boolean isContinuous(List<Integer> pages) {
			return pages.get(1) == pages.get(0) + 1;
		}

		private boolean isNotFirstPageOrLastPage(List<Integer> pages) {
			return pages.get(0) != FIRST_PAGE && pages.get(0) != LAST_PAGE
				&& pages.get(1) != FIRST_PAGE && pages.get(1) != LAST_PAGE;
		}

		private boolean isSizeTwo(List<Integer> pages) {
			return pages.size() == 2;
		}
	}

	static class Calculation {
		public int getMaxScore(List<Integer> pages) {
			return Math.max(plusOrMultiple(pages.get(0)), plusOrMultiple(pages.get(1)));
		}

		private int plusOrMultiple(int page) {
			return Math.max(plus(page), multiple(page));
		}

		private int plus(int page) {
			int result = 0;
			while (page != 0) {
				result += page % 10;
				page /= 10;
			}
			return result;
		}

		private int multiple(int page) {
			int result = 1;
			while (page != 0) {
				result *= page % 10;
				page /= 10;
			}
			return result;
		}
	}

	static class Output {
		private static final int POBI_WIN = 1;
		private static final int CRONG_WIN = 2;
		private static final int DRAW = 0;
		private static final int EXCEPTION = -1;

		public int getOutput(int pobiScore, int crongScore) {
			if (pobiScore > crongScore) {
				return POBI_WIN;
			}
			if (pobiScore < crongScore) {
				return CRONG_WIN;
			}
			return DRAW;
		}
	}
}
