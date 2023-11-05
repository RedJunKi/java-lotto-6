package lotto.view;

import lotto.constant.DisplayMessages;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static lotto.constant.DisplayMessages.*;

public class OutputView {

    public static void purchaseCount(int count) {
        System.out.println(PURCHASE_QUANTITY
                .format(String.valueOf(count)));
    }

    public static void displayLottoPapers(LottoMachine lottoMachine) {
        List<Lotto> lottoPapers = lottoMachine.getLottoPapers();
        for (Lotto lotto : lottoPapers) {
            StringBuilder sortLottoNumber = new StringBuilder("[");
            SortLottoNumber(sortLottoNumber, lotto);
            sortLottoNumber.delete(sortLottoNumber.length() - 2, sortLottoNumber.length());
            sortLottoNumber.append("]");
            System.out.println(sortLottoNumber);
        }

    }

    private static void SortLottoNumber(StringBuilder sortLottoNumber, Lotto lotto) {
        lotto.getNumbers().forEach(num -> sortLottoNumber.append(num).append(", "));
    }

    public static void displayResults(LottoResult lottoResult) {
        System.out.println(PRIZE_STATISTICS.getMessage());
        System.out.println(SEPARATOR.getMessage());
        Map<LottoRank, Integer> lottoPrizeResult = lottoResult.getLOTTO_RESULT();
        lottoPrizeResult.forEach((key, value) -> {
            checkSecondPrize(key, value);
            checkAnotherPrize(key, value);
            System.out.println();
        });
    }

    private static void checkAnotherPrize(LottoRank key, Integer value) {
        if (key != LottoRank.SECOND_RANK && key != LottoRank.NO_RANK) {
            System.out.printf(String.valueOf(MATCH_COUNT.getMessage()), key.getMatchCount(), key.getPrizeMoney(), value);

        }
    }

    private static void checkSecondPrize(LottoRank key, Integer value) {
        if (key == LottoRank.SECOND_RANK) {
            System.out.printf(String.valueOf(MATCH_COUNT_BONUS.getMessage()), key.getMatchCount(), key.getPrizeMoney(), value);
        }
    }

    public static void displayProfitMargin(double profitMargin) {
        System.out.println(TOTAL_RETURN.format(profitMargin));
    }
}