package store.view;

public class ContinueInputView extends InputViewTemplate<Boolean>{

    @Override
    public void printHeader(Object... args) {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    }

    @Override
    public Boolean bind(String userInput) {
        return ViewUtils.readYN(userInput);
    }
}
