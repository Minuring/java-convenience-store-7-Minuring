package store.loader;

import java.time.LocalDate;
import java.util.regex.Pattern;
import store.promotion.Promotion;

public class PromotionsLoader extends FileLoader<Promotion> {

    private static final Pattern PATTERN = Pattern.compile(
        "^\\S+,\\d,\\d,\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]),\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");

    public PromotionsLoader(String path) {
        super(path);
    }

    @Override
    protected Promotion bindLine(String line) {
        validateLine(line);
        String[] elements = line.split(",");

        String name = elements[0];
        int buy = Integer.parseInt(elements[1]);
        int get = Integer.parseInt(elements[2]);
        LocalDate startDate = LocalDate.parse(elements[3]);
        LocalDate endDate = LocalDate.parse(elements[4]);

        return Promotion.of(name, buy, get, startDate, endDate);
    }

    private void validateLine(String line) {
        boolean matches = PATTERN.matcher(line).matches();
        if (!matches) {
            throw new InvalidFileFormatException();
        }
    }
}
