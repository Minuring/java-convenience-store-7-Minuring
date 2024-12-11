package store.view;

public class MembershipInputView extends InputViewTemplate<Boolean>{

    @Override
    public void printHeader(Object... args) {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    @Override
    public Boolean bind(String userInput) {
        return ViewUtils.readYN(userInput);
    }
}
