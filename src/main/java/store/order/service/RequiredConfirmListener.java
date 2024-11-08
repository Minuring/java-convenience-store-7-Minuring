package store.order.service;

import java.util.function.BiFunction;

@FunctionalInterface
public interface RequiredConfirmListener<T, U> extends BiFunction<T, U, Boolean> {
    Boolean apply(T t, U u);
}
