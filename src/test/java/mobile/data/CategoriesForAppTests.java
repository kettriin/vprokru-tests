package mobile.data;

public enum CategoriesForAppTests {
    FRUITS("Овощи, фрукты, ягоды"),
    DAIRY("Молоко, сыр, яйца"),
    MEAT("Мясо, птица, колбасы");

    public final String categoryName;

    CategoriesForAppTests(String categoryName) {
        this.categoryName = categoryName;
    }
}
