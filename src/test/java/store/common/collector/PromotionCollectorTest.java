package store.common.collector;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.FilePath;
import store.discount.promotion.domain.Promotion;

class PromotionCollectorTest {

    @DisplayName("promotions.md 파일을 읽어 리스트로 변환한다.")
    @Test
    void collect() throws Exception {
        PromotionCollector promotionCollector = new PromotionCollector(FilePath.PROMOTIONS);

        List<Promotion> result = promotionCollector.collect();

        assertThat(result).hasSize(3);
        assertThat(result.getFirst().getName()).isEqualTo("탄산2+1");
        assertThat(result.getLast().getName()).isEqualTo("반짝할인");
    }
}