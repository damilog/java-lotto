package lotto.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Prize;
import lotto.model.WinPrizes;
import lotto.model.WinnerLotto;

public class PrizeStatus {

	public static final int ADDITION_PRIZE_COUNT = 1;
	public static final int INITIAL_PRIZE_COUNT = 0;

	public static WinPrizes findWinPrize(Lottos lottos, WinnerLotto winnerLotto) {
		Map<Prize, Integer> winPrizes = Prize.getValuesMoneySort()
			.stream()
			.collect(Collectors.toMap(prize -> prize, prize -> INITIAL_PRIZE_COUNT, (a, b) -> b, LinkedHashMap::new));
		for (Lotto lotto : lottos.getLottos()) {
			Prize winPrize = Prize.getWinnersStatus(
				(int)lotto.getLottoNumbers()
					.stream()
					.filter(winnerLotto.getWinnerLotto().getLottoNumbers()::contains)
					.count(), lotto.getLottoNumbers().contains(winnerLotto.getBonusNumber()));
			winPrizes.computeIfPresent(winPrize, (oldPrize, oldPrizeCount) -> oldPrizeCount + ADDITION_PRIZE_COUNT);
		}
		return new WinPrizes(winPrizes);
	}

}