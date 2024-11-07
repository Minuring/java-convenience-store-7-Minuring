package store.common.collector;

import java.time.LocalDate;
import store.constant.FilePath;
import store.discount.promotion.domain.Promotion;

public class PromotionCollector extends FileContentCollector<Promotion> {

    public PromotionCollector(FilePath filePath) {
        super(filePath);
    }

    @Override
    protected Promotion toInstance(String line, long sequence) {
        String[] split = line.trim().split(",");

        String name = split[0];
        int buy = Integer.parseInt(split[1]);
        int get = Integer.parseInt(split[2]);
        LocalDate start_date = LocalDate.parse(split[3]);
        LocalDate end_date = LocalDate.parse(split[4]);

        return new Promotion(name, buy, get, start_date, end_date);
    }
}
