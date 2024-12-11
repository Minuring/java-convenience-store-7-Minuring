package store.view;

import store.view.exception.BadInputException;

public class ViewUtils {

    public static boolean readYN(String userInput) {
        if (userInput.equalsIgnoreCase("Y")) {
            return true;
        }
        if (userInput.equalsIgnoreCase("N")) {
            return false;
        }
        throw new BadInputException();
    }

}
