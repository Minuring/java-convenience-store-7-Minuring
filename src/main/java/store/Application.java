package store;

import store.config.AppConfig;

public class Application {

    public static void main(String[] args) {
        ConvenienceStore convenienceStore = AppConfig.CONVENIENCE_STORE;
        convenienceStore.enter();
    }
}
