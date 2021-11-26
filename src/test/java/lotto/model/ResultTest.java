package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("result가 주어졌을 때 해당 결과에 맞는 당첨금이 반환되는지 검증")
    @Test
    void winningAmountTest() {
        Result result = new Result();
        result.add(3);
        result.add(4);
        result.add(5);
        result.add(6);

        assertThat(result.calculateWinningAmount()).isEqualTo(2_001_555_000);
    }

    @DisplayName("result가 주어졌을 때 해당 결과에 맞는 수익률이 반환되는지 검증")
    @Test
    void ratioTest() {
        Result result = new Result();
        result.add(3);

        assertThat(result.calculateRatio(14000)).isEqualTo(0.35);
    }
}