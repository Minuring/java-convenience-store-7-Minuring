package store.view;

public class NotAppliedPartlyInputView extends InputViewTemplate<Boolean> {

    @Override
    public void printHeader(Object... args) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", args);
    }

    @Override
    public Boolean bind(String userInput) {
        return ViewUtils.readYN(userInput);
    }
}
