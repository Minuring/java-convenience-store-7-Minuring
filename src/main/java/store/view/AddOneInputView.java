package store.view;

public class AddOneInputView extends InputViewTemplate<Boolean> {

    @Override
    public void printHeader(Object... args) {
        System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", args);
    }

    @Override
    public Boolean bind(String userInput) {
        return ViewUtils.readYN(userInput);
    }
}
