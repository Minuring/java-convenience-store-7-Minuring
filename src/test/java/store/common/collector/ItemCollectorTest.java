package store.common.collector;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.FilePath;
import store.item.domain.Item;
import store.mock.MockPromotions;

class ItemCollectorTest {

    @DisplayName("products.md 파일을 읽어 리스트로 변환한다.")
    @Test
    void collect() throws Exception {
        ItemCollector itemCollector = new ItemCollector(FilePath.PRODUCTS, new MockPromotions());

        List<Item> result = itemCollector.collect();

        assertThat(result).hasSize(16);
        assertThat(result.getFirst().getName()).isEqualTo("콜라");
        assertThat(result.getLast().getName()).isEqualTo("컵라면");
    }
}