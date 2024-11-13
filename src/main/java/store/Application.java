package store;

import store.config.AppConfig;
import store.facade.ConvenienceStore;

public class Application {

    public static void main(String[] args) {
        ConvenienceStore convenienceStore = AppConfig.CONVENIENCE_STORE;
        convenienceStore.setUp();
        convenienceStore.enter();
    }
}
