package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int paperCount;
        LottoMachine lottoMachine = new LottoMachine();
        int amount = lottoMachine.purchaseLotto();
        WinningNumbers winningNumbers = new WinningNumbers();
        LotteryChecker lotteryChecker = new LotteryChecker();
        ProfitCalculator profitCalculator = new ProfitCalculator(lotteryChecker);

        while (true) {
            if (lottoMachine.calculateAmount(amount) != -1) {
                paperCount = lottoMachine.calculateAmount(amount);
                break;
            }
            amount = lottoMachine.purchaseLotto();
        }

        OutputView.purchaseCount(paperCount);

        List<Lotto> lottoList = lottoMachine.buyLotto(paperCount);
        for (Lotto lotto : lottoList) {
            lotto.printLottoNumbers();
        }
        System.out.println();

        List<Integer> winningNumber = winningNumbers.createWinningNumber();

        System.out.println();

        List<Integer> bonusNumber = winningNumbers.createBonusNumber();

        lotteryChecker.prizeCheck(lottoList, winningNumber);

        for (Lotto lotto : lottoList) {
            int prize = lotto.getPrize();
        }

        List<Integer> integers = lotteryChecker.prizeCalculate(lottoList);

        profitCalculator.calculate(integers);
        int totalSum = profitCalculator.getTotalSum();
        System.out.println("총 상금 : " + totalSum);

        double v = profitCalculator.earnRate(totalSum, amount);
        System.out.println("총 수익률은 " + v + "%입니다.");
    }
}
