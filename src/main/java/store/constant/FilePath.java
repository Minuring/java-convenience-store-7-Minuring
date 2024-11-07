package store.constant;

public enum FilePath {
    PRODUCTS("products.md"),
    PROMOTIONS("promotions.md");

    private final String name;

    FilePath(String name) {
        this.name = name;
    }

    public String get() {
        return System.getProperty("user.dir") + "/src/main/resources/" + name;
    }
}